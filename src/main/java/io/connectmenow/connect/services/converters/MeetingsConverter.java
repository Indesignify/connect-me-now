package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDataDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import io.connectmenow.connect.model.entities.MeetingsEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = UserConverter.class)
@Component
public interface MeetingsConverter {

  UserConverter userConverter = Mappers.getMapper(UserConverter.class);
  MeetingsConverter meetingsConverter = Mappers.getMapper(MeetingsConverter.class);

  // MeetingsEntity <-> MeetingsDTO
//  MeetingsEntity convertMeetingsDTOToMeetingsEntity(MeetingsDTO meetingsDTO);
//  List<MeetingsEntity> convertMeetingsDTOToMeetingsEntityList(List<MeetingsDTO> meetingsDTOList);
  MeetingsDTO convertMeetingsEntityToMeetingsDTO(MeetingsEntity meetingsEntity);
  List<MeetingsDTO> convertMeetingsEntityToMeetingsDTOList(List<MeetingsEntity> meetingsEntityList);

  // MeetingsEntity <-> CreateMeetingsDTO
  MeetingsEntity convertCreateMeetingsDTOToMeetingsEntity(CreateMeetingsDTO createMeetingsDTO);
  CreateMeetingsDTO convertMeetingsEntityToCreateMeetingsDTO(MeetingsEntity meetingsEntity);

  // MeetingsEntity <-> UpdateMeetingsDataDTO
  MeetingsEntity convertUpdateMeetingsDataDTOToMeetingsEntity(UpdateMeetingsDataDTO updateMeetingsDataDTO);
  UpdateMeetingsDataDTO convertMeetingsEntityToUpdateMeetingsDataDTO(MeetingsEntity meetingsEntity);

  // THIS IS TOTALLY WRONG AND NEED TO BE FIXED
  MeetingParticipantEntity convertUserParticipantDTOToMeetingParticipantEntity(
      UserParticipantDTO userParticipantDTO);

  // MeetingsDTO <-> CreateMeetingsDTO
//  MeetingsDTO convertCreateMeetingDTOToMeetingsDTO(CreateMeetingsDTO createMeetingsDTO);
//  CreateMeetingsDTO convertMeetingsDTOToCreateMeetingsDTO(MeetingsDTO meetingsDTO);

  default Set<UserParticipantDTO> convert(Set<MeetingParticipantEntity> meetingParticipantEntities) {
    Set<UserParticipantDTO> userDTOSet = new LinkedHashSet<>();
    meetingParticipantEntities.forEach(entity -> userDTOSet.add(convert(entity)));
    return userDTOSet;
  }

  default UserParticipantDTO convert(MeetingParticipantEntity meetingParticipantEntity) {
    UsersEntity usersEntity = meetingParticipantEntity.getUser();

    UserParticipantDTO userParticipantDTO =
        userConverter.convertUsersEntityToUserParticipantDTO(usersEntity);

    userParticipantDTO.setParticipationStatus(meetingParticipantEntity.getParticipationStatus());
    userParticipantDTO.setUserCoordinateX(meetingParticipantEntity.getUserCoordinateX());
    userParticipantDTO.setUserCoordinateY(meetingParticipantEntity.getUserCoordinateY());

    return userParticipantDTO;
  }

}
