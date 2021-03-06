package io.connectmenow.connect.services.impl;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDataDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsParticipantsDTO;
import io.connectmenow.connect.model.dto.UpdateUserCoordinatesDTO;
import io.connectmenow.connect.model.dto.UserCoordinatesDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingStatus;
import io.connectmenow.connect.model.entities.MeetingsEntity;
import io.connectmenow.connect.model.entities.ParticipationStatus;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.MeetingParticipantRepository;
import io.connectmenow.connect.repository.MeetingsRepository;
import io.connectmenow.connect.repository.UserRepository;
import io.connectmenow.connect.services.FriendsService;
import io.connectmenow.connect.services.MeetingsService;
import io.connectmenow.connect.services.converters.MeetingParticipantConverter;
import io.connectmenow.connect.services.converters.MeetingsConverter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("meetingsService")
@Transactional
public class MeetingsServiceImpl implements MeetingsService {

  @Autowired
  MeetingsRepository meetingsRepository;

  @Autowired
  MeetingsConverter meetingsConverter;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MeetingParticipantRepository meetingParticipantRepository;

  @Autowired
  MeetingParticipantConverter meetingParticipantConverter;

  @Autowired
  FriendsService friendsService;

  @Value("${application.meeting-radius}")
  private Double meetingRadius;

  @Override
  public MeetingsDTO getMeetingById(Long meetingId) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).orElse(null);

    if (meetingsEntity == null) {
      return null;
    }

    return meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity);

  }

  @Override
  public List<MeetingsDTO> getAllMeetings() {

    List<MeetingsDTO> allMeetingsList = new ArrayList<>();

    meetingsRepository.findAll().forEach(meetingsEntity ->
        allMeetingsList.add(meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity)));

    return allMeetingsList;

  }

  @Override
  public Set<UserParticipantDTO> getMeetingParticipants(Long meetingId) {

    MeetingsEntity meeting = meetingsRepository.findById(meetingId).get();

    Set<MeetingParticipantEntity> meetingParticipantEntities = meeting.getMeetingParticipants();

    Set<UserParticipantDTO> participants = meetingsConverter.convert(meetingParticipantEntities);

    return participants;

  }

  @Override
  public MeetingsDTO createMeeting(CreateMeetingsDTO createMeetingsDTO) {

    final MeetingsEntity meetingEntity = meetingsRepository.save(meetingsConverter
        .convertCreateMeetingsDTOToMeetingsEntity(createMeetingsDTO));

    Long creatorId = createMeetingsDTO.getCreatorId();

    createMeetingsDTO.getMeetingParticipantsIds().forEach(pId -> {
      if (!(friendsService.areFriends(pId, creatorId)) && (pId != creatorId)) {
        throw new IllegalArgumentException("User with id " + pId + " is not a friend of "
            + "the meeting creator with id " + creatorId + "!");
      }

      MeetingParticipantEntity meetingParticipantEntity = MeetingParticipantEntity.builder()
          .meeting(meetingEntity)
          .participationStatus(ParticipationStatus.INVITED)
          .user(userRepository.findById(pId).get())
          .userCoordinateX(0.0)
          .userCoordinateY(0.0)
          .build();

      if (pId == creatorId) {
        meetingParticipantEntity.setParticipationStatus(ParticipationStatus.CREATOR);
      }

      MeetingParticipantEntity savedMeetingParticipantEntity =
          meetingParticipantRepository.save(meetingParticipantEntity);
      meetingEntity.getMeetingParticipants().add(savedMeetingParticipantEntity);

      meetingsRepository.save(meetingEntity);

      UsersEntity usersEntity = userRepository.findById(pId).get();
      usersEntity.getMeetingsOfUser().add(meetingParticipantEntity);
      userRepository.save(usersEntity);

    });

    UsersEntity creatorUsersEntity = userRepository.findById(creatorId).get();

    if (meetingParticipantRepository
        .findMeetingParticipantEntityByUserIdAndMeetingId(creatorId, meetingEntity.getId()) == null) {

      MeetingParticipantEntity meetingParticipantEntity = MeetingParticipantEntity.builder()
          .meeting(meetingEntity)
          .participationStatus(ParticipationStatus.CREATOR)
          .user(userRepository.findById(creatorId).get())
          .userCoordinateX(0.0)
          .userCoordinateY(0.0)
          .build();

      MeetingParticipantEntity savedMeetingParticipantEntity =
          meetingParticipantRepository.save(meetingParticipantEntity);
      meetingEntity.getMeetingParticipants().add(savedMeetingParticipantEntity);

      creatorUsersEntity.getMeetingsOfUser().add(meetingParticipantEntity);
      userRepository.save(creatorUsersEntity);

    }

    meetingsRepository.save(meetingEntity);

    MeetingsDTO newMeeting = meetingsConverter
          .convertMeetingsEntityToMeetingsDTO(meetingsRepository
              .findById(meetingEntity.getId()).get());

    return newMeeting;

  }

  @Override
  public UserCoordinatesDTO getUserCoordinates(Long userId, Long meetingId) {

    MeetingParticipantEntity meetingParticipantEntity = meetingParticipantRepository
        .findMeetingParticipantEntityByUserIdAndMeetingId(userId, meetingId);

    if (meetingParticipantEntity.getParticipationStatus() == ParticipationStatus.ACCEPTED) {
        UserCoordinatesDTO userCoordinatesDTO = UserCoordinatesDTO
            .builder()
            .userId(userId)
            .meetingId(meetingId)
            .userCoordinateX(meetingParticipantEntity.getUserCoordinateX())
            .userCoordinateY(meetingParticipantEntity.getUserCoordinateY())
            .build();

        return userCoordinatesDTO;
    } else if (meetingParticipantEntity.getParticipationStatus() == ParticipationStatus.REJECTED) {
        throw new IllegalArgumentException("It is impossible to get coordinates of user with id "
                            + userId + " in meeting with meetingId " + meetingId + " because user "
                            + "with " + userId + " rejected invitation");
    } else {
      UserCoordinatesDTO userCoordinatesDTO = UserCoordinatesDTO
          .builder()
          .userId(userId)
          .meetingId(meetingId)
          .userCoordinateX(0.0)
          .userCoordinateY(0.0)
          .build();

      return userCoordinatesDTO;
    }
  }

  @Override
  public UserCoordinatesDTO updateUserCoordinates(Long userId, Long meetingId, UpdateUserCoordinatesDTO updateUserCoordinatesDTO) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).get();

    if (meetingsEntity.getMeetingStatus() != MeetingStatus.ONGOING) {
      throw new IllegalStateException("Meeting is already over, can't update user coordinates!");
    }

    MeetingParticipantEntity meetingParticipantEntity = meetingParticipantRepository
        .findMeetingParticipantEntityByUserIdAndMeetingId(userId, meetingId);

    if (meetingParticipantEntity.getParticipationStatus() == ParticipationStatus.REJECTED) {
      throw new IllegalArgumentException("User with id " + userId + " rejected meeting with id "
          + meetingId + ", can't update coordinates!");
    }

    UserCoordinatesDTO userCoordinatesDTO = meetingsConverter.convert(updateUserCoordinatesDTO);

    userCoordinatesDTO.setMeetingId(meetingParticipantEntity.getMeetingId());
    userCoordinatesDTO.setUserId(meetingParticipantEntity.getUserId());

    meetingParticipantEntity.setUserCoordinateX(updateUserCoordinatesDTO.getUserCoordinateX());

    meetingParticipantEntity.setUserCoordinateY(updateUserCoordinatesDTO.getUserCoordinateY());

    meetingParticipantRepository.save(meetingParticipantEntity);

    if (meetingIsCompletedAutomatically(meetingId)) {
      completeMeeting(meetingId);
    }

    return userCoordinatesDTO;

  }

  @Override
  public MeetingsDTO updateMeetingData(Long meetingId, UpdateMeetingsDataDTO updateMeetingsDataDTO) {

    updateMeetingsDataDTO.setId(meetingId);

    MeetingsEntity meetingsEntityToUpdate = meetingsRepository.findById(meetingId).orElse(null);

    if (updateMeetingsDataDTO.getTitle() != null) {
      meetingsEntityToUpdate.setTitle(updateMeetingsDataDTO.getTitle());
    }

    if (updateMeetingsDataDTO.getMeetingStatus() != null) {
      meetingsEntityToUpdate.setMeetingStatus(updateMeetingsDataDTO.getMeetingStatus());
    }

    if (updateMeetingsDataDTO.getCoordinateX() != null) {
      meetingsEntityToUpdate.setCoordinateX(updateMeetingsDataDTO.getCoordinateX());
    }

    if (updateMeetingsDataDTO.getCoordinateY() != null) {
      meetingsEntityToUpdate.setCoordinateY(updateMeetingsDataDTO.getCoordinateY());
    }

    meetingsRepository.save(meetingsEntityToUpdate);

    MeetingsDTO updatedMeetingsDTO = meetingsConverter
        .convertMeetingsEntityToMeetingsDTO(meetingsEntityToUpdate);

    return updatedMeetingsDTO;

  }

  @Override
  public MeetingsDTO updateMeetingParticipants(Long meetingId,
      UpdateMeetingsParticipantsDTO updateMeetingsParticipantsDTO) {

    updateMeetingsParticipantsDTO.setId(meetingId);

    MeetingsEntity meetingsEntityToUpdate = meetingsRepository.findById(meetingId).get();

    updateMeetingsParticipantsDTO
        .getNewParticipants()
        .forEach(mPartEnt -> {
          MeetingParticipantEntity newMeetingParticipantEntity =
              MeetingParticipantEntity.builder()
                  .meeting(meetingsRepository.findById(meetingId).get())
                  .user(userRepository.findById(mPartEnt.getId()).get())
                  .participationStatus(ParticipationStatus.INVITED)
                  .userCoordinateX(0.0)
                  .userCoordinateY(0.0)
                  .build();
          meetingParticipantRepository.save(newMeetingParticipantEntity);

          meetingsEntityToUpdate.getMeetingParticipants().add(newMeetingParticipantEntity);

        } );

    meetingsRepository.save(meetingsEntityToUpdate);

    MeetingsDTO updatedMeetingsDTO = meetingsConverter
        .convertMeetingsEntityToMeetingsDTO(meetingsEntityToUpdate);

    return updatedMeetingsDTO;

  }

  @Override
  public MeetingsDTO cancelMeeting(Long meetingId, Long userId) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).get();

    Long creatorId = 0L;

    for (MeetingParticipantEntity meetingParticipantEnt : meetingsEntity.getMeetingParticipants()) {
      if (meetingParticipantEnt.getParticipationStatus() == ParticipationStatus.CREATOR) {
        creatorId = meetingParticipantEnt.getUserId();
      }
    }

    if (creatorId == 0) {
      throw new IllegalStateException("Meeting has no creator, something went wrong!");
    }

    if (userId != creatorId) {
      throw new IllegalStateException("User with id " + userId + " is not a creator of meeting with id " +
          meetingId + "! Can't cancel meeting.");
    }

    meetingsEntity.setMeetingStatus(MeetingStatus.CANCELLED);

    meetingsEntity.setEndedAt(new Timestamp(System.currentTimeMillis()));

    return meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity);

  }

  @Override
  public UserParticipantDTO acceptMeeting(Long userId, Long meetingId) {

    MeetingParticipantEntity meetingParticipantEntity = meetingParticipantRepository
        .findMeetingParticipantEntityByUserIdAndMeetingId(userId, meetingId);

    if (meetingParticipantEntity.getParticipationStatus() == ParticipationStatus.CREATOR) {
      throw new IllegalArgumentException("User with id " + userId + " is a creator of meeting "
          + "with id " + meetingId + ", can't accept meeting!");
    }

    meetingParticipantEntity.setParticipationStatus(ParticipationStatus.ACCEPTED);

    meetingParticipantRepository.save(meetingParticipantEntity);

    return meetingParticipantConverter
        .convertMeetingParticipantEntityToUserParticipantDTO(meetingParticipantEntity);

  }

  @Override
  public UserParticipantDTO rejectMeeting(Long userId, Long meetingId) {

    MeetingParticipantEntity meetingParticipantEntity = meetingParticipantRepository
        .findMeetingParticipantEntityByUserIdAndMeetingId(userId, meetingId);

    meetingParticipantEntity.setParticipationStatus(ParticipationStatus.REJECTED);
    meetingParticipantEntity.setUserCoordinateX(Double.NEGATIVE_INFINITY);
    meetingParticipantEntity.setUserCoordinateY(Double.NEGATIVE_INFINITY);

    meetingParticipantRepository.save(meetingParticipantEntity);

    return meetingParticipantConverter
        .convertMeetingParticipantEntityToUserParticipantDTO(meetingParticipantEntity);

  }

  @Override
  public MeetingsDTO completeMeeting(Long meetingId) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).orElse(null);

    if (meetingsEntity == null) {
      throw new IllegalStateException("Meeting with id " + meetingId + " was "
          + "not found, something went wrong!");
    }

    if (meetingIsCompletedAutomatically(meetingId)) {
      meetingsEntity.setMeetingStatus(MeetingStatus.SUCCESSFUL);
    } else {
      meetingsEntity.setMeetingStatus(MeetingStatus.PARTIALLY_SUCCESSFUL);
    }

    meetingsEntity.setEndedAt(new Timestamp(System.currentTimeMillis()));

    meetingsRepository.save(meetingsEntity);

    return meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity);

  }

  @Override
  public Boolean meetingIsCompletedAutomatically(Long meetingId) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).get();

    Set<MeetingParticipantEntity> activeMeetingParticipants = new HashSet<>();

    Long numberOfParticipantsInPlace = 0L;

    for (MeetingParticipantEntity meetingPartEnt : meetingsEntity.getMeetingParticipants()) {
      if (meetingPartEnt.getParticipationStatus() == ParticipationStatus.ACCEPTED ||
          meetingPartEnt.getParticipationStatus() == ParticipationStatus.CREATOR) {
        activeMeetingParticipants.add(meetingPartEnt);
      }
    }

    for (MeetingParticipantEntity actPart : activeMeetingParticipants) {
      if ((actPart.getUserCoordinateX() - meetingsEntity.getCoordinateX())
          * (actPart.getUserCoordinateX() - meetingsEntity.getCoordinateX())
          + (actPart.getUserCoordinateY() - meetingsEntity.getCoordinateY())
          * (actPart.getUserCoordinateY() - meetingsEntity.getCoordinateY())
          <= meetingRadius * meetingRadius) {
            numberOfParticipantsInPlace++;
      }
    }

    return numberOfParticipantsInPlace == activeMeetingParticipants.size();

  }

}
