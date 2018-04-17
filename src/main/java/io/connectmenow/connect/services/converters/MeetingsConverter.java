package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDTO;
import io.connectmenow.connect.model.entities.MeetingsEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MeetingsConverter {

  // MeetingsEntity <-> MeetingsDTO
  MeetingsEntity convertMeetingsDTOToMeetingsEntity(MeetingsDTO meetingsDTO);
  List<MeetingsEntity> convertMeetingsDTOToMeetingsEntityList(List<MeetingsDTO> meetingsDTOList);
  MeetingsDTO convertMeetingsEntityToMeetingsDTO(MeetingsEntity meetingsEntity);
  List<MeetingsDTO> convertMeetingsEntityToMeetingsDTOList(List<MeetingsEntity> meetingsEntityList);

  // MeetingsEntity <-> CreateMeetingsDTO
  MeetingsEntity convertCreateMeetingsDTOToMeetingsEntity(CreateMeetingsDTO createMeetingsDTO);
  CreateMeetingsDTO convertMeetingsEntityToCreateMeetingsDTO(MeetingsEntity meetingsEntity);

  // MeetingsEntity <-> UpdateMeetingsDTO
  MeetingsEntity convertUpdateMeetingsDTOToMeetingsEntity(UpdateMeetingsDTO updateMeetingsDTO);
  UpdateMeetingsDTO convertMeetingsEntityToUpdateMeetingsDTO(MeetingsEntity meetingsEntity);

  // MeetingsDTO <-> CreateMeetingsDTO
  MeetingsDTO convertCreateMeetingDTOToMeetingsDTO(CreateMeetingsDTO createMeetingsDTO);
  CreateMeetingsDTO convertMeetingsDTOToCreateMeetingsDTO(MeetingsDTO meetingsDTO);

}
