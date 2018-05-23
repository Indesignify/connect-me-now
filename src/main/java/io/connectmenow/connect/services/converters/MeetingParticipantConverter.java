package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.MeetingParticipantDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UserMeetingInfoDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import io.connectmenow.connect.model.entities.MeetingsEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.MeetingParticipantRepository;
import io.connectmenow.connect.repository.MeetingsRepository;
import io.connectmenow.connect.repository.UserRepository;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = MeetingsConverter.class)
@Component
public abstract class MeetingParticipantConverter {

  MeetingsConverter meetingsConverter
      = Mappers.getMapper(MeetingsConverter.class);

  @Autowired
  protected UserRepository userRepository;

  @Autowired
  protected MeetingsRepository meetingsRepository;

  @Autowired
  protected MeetingParticipantRepository meetingParticipantRepository;

  public MeetingParticipantEntity convertMeetingParticipantDTOToMeetingParticipantEntity
      (MeetingParticipantDTO meetingParticipantDTO) {

    MeetingParticipantEntity meetingParticipantEntity = meetingParticipantRepository
        .findMeetingParticipantEntityByMeetingId(meetingParticipantDTO.getMeetingId());

    return meetingParticipantEntity;

  }

  public MeetingParticipantDTO convertMeetingParticipantEntityToMeetingParticipantDTO
      (MeetingParticipantEntity meetingParticipantEntity) {
    MeetingsEntity meetingsEntity =
        meetingsRepository.findById(meetingParticipantEntity.getMeetingId()).get();

    MeetingParticipantDTO meetingParticipantDTO = MeetingParticipantDTO
        .builder()
        .meetingId(meetingsEntity.getId())
        .title(meetingsEntity.getTitle())
        .participants(convert(meetingsEntity.getMeetingParticipants()))
        .build();

    return meetingParticipantDTO;
  }

  public UserParticipantDTO convertMeetingParticipantEntityToUserParticipantDTO(
      MeetingParticipantEntity meetingParticipantEntity) {
    UsersEntity usersEntity = userRepository.findById(meetingParticipantEntity.getUserId()).get();

    UserParticipantDTO userParticipantDTO = UserParticipantDTO
        .builder()
        .id(meetingParticipantEntity.getUserId())
        .firstName(usersEntity.getFirstName())
        .lastName(usersEntity.getLastName())
        .nickname(usersEntity.getNickname())
        .participationStatus(meetingParticipantEntity.getParticipationStatus())
        .userCoordinateX(meetingParticipantEntity.getUserCoordinateX())
        .userCoordinateY(meetingParticipantEntity.getUserCoordinateY())
        .build();

    return userParticipantDTO;
  }

  public Set<UserParticipantDTO> convertMeetingParticipantEntitySetToUserParticipantDTOSet(
      Set<MeetingParticipantEntity> meetingParticipantEntities) {
    Set<UserParticipantDTO> userParticipantDTOS = new LinkedHashSet<>();
    meetingParticipantEntities.forEach(entity -> userParticipantDTOS
        .add(convertMeetingParticipantEntityToUserParticipantDTO(entity)));
    return userParticipantDTOS;
  }

  public Set<MeetingsDTO> convertMeetingsDTOSetToMeetingParticipantEntitySet(
      Set<MeetingParticipantEntity> meetingParticipantEntities) {
    Set<MeetingsDTO> meetingsDTOSet = new LinkedHashSet<>();
    meetingParticipantEntities.forEach(entity -> meetingsDTOSet
                                  .add(convertMeetingParticipantEntityToMeetingsDTO(entity)));
    return meetingsDTOSet;
  }

  public MeetingsDTO convertMeetingParticipantEntityToMeetingsDTO(
      MeetingParticipantEntity meetingParticipantEntity) {
    MeetingsEntity meetingsEntity =
        meetingsRepository.findById(meetingParticipantEntity.getMeetingId()).get();

    MeetingsDTO meetingsDTO = MeetingsDTO
        .builder()
        .id(meetingsEntity.getId())
        .title(meetingsEntity.getTitle())
        .createdAt(meetingsEntity.getCreatedAt())
        .endedAt(meetingsEntity.getEndedAt())
        .expiresAt(meetingsEntity.getExpiresAt())
        .meetingStatus(meetingsEntity.getMeetingStatus())
        .coordinateX(meetingsEntity.getCoordinateX())
        .coordinateY(meetingsEntity.getCoordinateY())
        .meetingParticipants(convertMeetingParticipantEntitySetToUserParticipantDTOSet(meetingsEntity.getMeetingParticipants()))
        .build();

    return meetingsDTO;
  }

  public UserMeetingInfoDTO convert(MeetingParticipantEntity meetingParticipantEntity) {
    UsersEntity usersEntity = userRepository.findById(meetingParticipantEntity.getUserId()).get();

    UserMeetingInfoDTO userMeetingInfoDTO = UserMeetingInfoDTO
        .builder()
        .userId(usersEntity.getId())
        .firstName(usersEntity.getFirstName())
        .lastName(usersEntity.getLastName())
        .nickname(usersEntity.getNickname())
        .build();

    return userMeetingInfoDTO;
  }

  public Set<UserMeetingInfoDTO> convert(Set<MeetingParticipantEntity> meetingParticipantEntities) {
    Set<UserMeetingInfoDTO> userMeetingInfoDTOSet = new LinkedHashSet<>();
    meetingParticipantEntities.forEach(entity -> userMeetingInfoDTOSet.add(convert(entity)));
    return userMeetingInfoDTOSet;
  }

}
