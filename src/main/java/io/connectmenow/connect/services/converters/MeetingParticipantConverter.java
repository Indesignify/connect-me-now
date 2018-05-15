package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.MeetingParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = MeetingsConverter.class)
@Component
public interface MeetingParticipantConverter {

  MeetingsConverter meetingsConverter
      = Mappers.getMapper(MeetingsConverter.class);

  MeetingParticipantEntity convert(MeetingParticipantDTO meetingParticipantDTO);
  MeetingParticipantDTO convert(MeetingParticipantEntity meetingParticipantEntity);

}
