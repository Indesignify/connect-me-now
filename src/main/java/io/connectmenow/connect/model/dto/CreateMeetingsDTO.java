package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.MeetingStatus;
import java.sql.Timestamp;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMeetingsDTO {

  private Timestamp expiresAt = new Timestamp(System.currentTimeMillis() + (6*60*60*1000));

  private MeetingStatus meetingStatus = MeetingStatus.ONGOING;

  private Set<Long> meetingParticipantsIds;

}
