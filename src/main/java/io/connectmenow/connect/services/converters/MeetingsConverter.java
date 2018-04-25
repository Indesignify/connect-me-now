package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import io.connectmenow.connect.model.entities.MeetingsEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = UserConverter.class)
@Component
public interface MeetingsConverter {

//  @Autowired
//  UserConverter userConverter;
  UserConverter userConverter = Mappers.getMapper(UserConverter.class);
  MeetingsConverter meetingsConverter = Mappers.getMapper(MeetingsConverter.class);

  // MeetingsEntity <-> MeetingsDTO
//  MeetingsEntity convertMeetingsDTOToMeetingsEntity(MeetingsDTO meetingsDTO);
//  List<MeetingsEntity> convertMeetingsDTOToMeetingsEntityList(List<MeetingsDTO> meetingsDTOList);
  MeetingsDTO convertMeetingsEntityToMeetingsDTO(MeetingsEntity meetingsEntity);
  List<MeetingsDTO> convertMeetingsEntityToMeetingsDTOList(List<MeetingsEntity> meetingsEntityList);

  // MeetingsEntity <-> CreateMeetingsDTO
  @Mapping(target = "meetingParticipants", ignore = true)
  MeetingsEntity convertCreateMeetingsDTOToMeetingsEntity(CreateMeetingsDTO createMeetingsDTO);
  CreateMeetingsDTO convertMeetingsEntityToCreateMeetingsDTO(MeetingsEntity meetingsEntity);

  // MeetingsEntity <-> UpdateMeetingsDTO
  MeetingsEntity convertUpdateMeetingsDTOToMeetingsEntity(UpdateMeetingsDTO updateMeetingsDTO);
  UpdateMeetingsDTO convertMeetingsEntityToUpdateMeetingsDTO(MeetingsEntity meetingsEntity);

  // MeetingsDTO <-> CreateMeetingsDTO
//  MeetingsDTO convertCreateMeetingDTOToMeetingsDTO(CreateMeetingsDTO createMeetingsDTO);
//  CreateMeetingsDTO convertMeetingsDTOToCreateMeetingsDTO(MeetingsDTO meetingsDTO);

  default Set<UserParticipantDTO> convert(Set<MeetingParticipantEntity> meetingParticipantEntities) {
    Set<UserParticipantDTO> userDTOSet = new HashSet<>();
    meetingParticipantEntities.forEach(entity -> userDTOSet.add(convert(entity)));
    return userDTOSet;
  }

  default UserParticipantDTO convert(MeetingParticipantEntity meetingParticipantEntity) {
    return userConverter.convertUsersEntityToUserParticipantDTO(meetingParticipantEntity.getUser());
  }

}
