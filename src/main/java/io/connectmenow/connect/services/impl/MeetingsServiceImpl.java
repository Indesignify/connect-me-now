package io.connectmenow.connect.services.impl;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDataDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsParticipantsDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingStatus;
import io.connectmenow.connect.model.entities.MeetingsEntity;
import io.connectmenow.connect.model.entities.ParticipationStatus;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.MeetingParticipantRepository;
import io.connectmenow.connect.repository.MeetingsRepository;
import io.connectmenow.connect.repository.UserRepository;
import io.connectmenow.connect.services.MeetingsService;
import io.connectmenow.connect.services.converters.MeetingsConverter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

  // stub
  @Override
  public List<UserParticipantDTO> getMeetingParticipants(Long meetingId) {

    MeetingsEntity meeting = meetingsRepository.findById(meetingId).get();

    Set<MeetingParticipantEntity> meetingParticipantEntities = meeting.getMeetingParticipants();

//    return meetingsConverter.convert(meetingParticipantEntities);

    return null;

  }

  @Override
  public MeetingsDTO createMeeting(CreateMeetingsDTO createMeetingsDTO) {

    final MeetingsEntity meetingEntity = meetingsRepository.save(meetingsConverter
        .convertCreateMeetingsDTOToMeetingsEntity(createMeetingsDTO));

    createMeetingsDTO.getMeetingParticipantsIds().forEach(pId -> {
      MeetingParticipantEntity meetingParticipantEntity = MeetingParticipantEntity.builder()
          .meeting(meetingEntity)
          .participationStatus(ParticipationStatus.INVITED)
          .user(userRepository.findById(pId).get())
          .userCoordinateX(0.0)
          .userCoordinateY(0.0)
          .build();
      MeetingParticipantEntity savedMeetingParticipantEntity =
          meetingParticipantRepository.save(meetingParticipantEntity);
      meetingEntity.getMeetingParticipants().add(savedMeetingParticipantEntity);

      UsersEntity usersEntity = userRepository.findById(pId).get();
      usersEntity.getMeetingsOfUser().add(meetingParticipantEntity);
      userRepository.save(usersEntity);

    });

    meetingsRepository.save(meetingEntity);

    MeetingsDTO newMeeting = meetingsConverter
          .convertMeetingsEntityToMeetingsDTO(meetingsRepository
              .findById(meetingEntity.getId()).get());

    return newMeeting;

  }

  @Override
  public MeetingsDTO updateMeetingData(Long meetingId, UpdateMeetingsDataDTO updateMeetingsDataDTO) {

    updateMeetingsDataDTO.setId(meetingId);

    MeetingsEntity meetingsEntityToUpdate = meetingsRepository.findById(meetingId).orElse(null);

    if (updateMeetingsDataDTO.getMeetingStatus() != null) {
      meetingsEntityToUpdate.setMeetingStatus(updateMeetingsDataDTO.getMeetingStatus());
    }

    if (updateMeetingsDataDTO.getCoordinateX() != null) {
      meetingsEntityToUpdate.setCoordinateX(updateMeetingsDataDTO.getCoordinateX());
    }

    if (updateMeetingsDataDTO.getCoordinateY() != null) {
      meetingsEntityToUpdate.setCoordinateY(updateMeetingsDataDTO.getCoordinateY());
    }

    MeetingsDTO updatedMeetingsDTO = meetingsConverter
        .convertMeetingsEntityToMeetingsDTO(meetingsEntityToUpdate);

    return updatedMeetingsDTO;

  }

  // temporary solution, doesn't work yet
  @Override
  public MeetingsDTO updateMeetingParticipants(Long meetingId,
      UpdateMeetingsParticipantsDTO updateMeetingsParticipantsDTO) {

    updateMeetingsParticipantsDTO.setId(meetingId);

    MeetingsEntity meetingsEntityToUpdate = meetingsRepository.findById(meetingId).get();

    updateMeetingsParticipantsDTO
        .getNewParticipants()
        .forEach(mPartEnt -> {
          MeetingParticipantEntity newMeetingParticipantEntity =
              meetingsConverter.convertUserParticipantDTOToMeetingParticipantEntity(mPartEnt);
          meetingsEntityToUpdate.getMeetingParticipants().add(newMeetingParticipantEntity);
        } );

    meetingsRepository.save(meetingsEntityToUpdate);

    MeetingsDTO updatedMeetingsDTO = meetingsConverter
        .convertMeetingsEntityToMeetingsDTO(meetingsEntityToUpdate);

    return updatedMeetingsDTO;

  }

  @Override
  public MeetingsDTO cancelMeeting(Long meetingId) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).get();

    meetingsEntity.setMeetingStatus(MeetingStatus.CANCELLED);

    meetingsEntity.setEndedAt(new Timestamp(System.currentTimeMillis()));

    return meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity);

  }

  // stub
  @Override
  public void acceptMeeting(Long userId, Long meetingId) {
    return;
  }

  // stub
  @Override
  public void rejectMeeting(Long userId, Long meetingId) {
    return;
  }

  @Override
  public MeetingsDTO completeMeeting(Long meetingId) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).orElse(null);

    meetingsEntity.setMeetingStatus(MeetingStatus.SUCCESSFUL);

    meetingsEntity.setEndedAt(new Timestamp(System.currentTimeMillis()));

    return meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity);

  }
}
