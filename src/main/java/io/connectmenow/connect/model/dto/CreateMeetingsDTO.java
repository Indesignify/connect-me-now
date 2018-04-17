package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.MeetingStatus;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMeetingsDTO {

  private Long requesterId;

  private Long responderId;

  private Double requesterCoordX;

  private Double requesterCoordY;

  private Double responderCoordX;

  private Double responderCoordY;

  private Timestamp createdAt;

  private Timestamp expiresAt = new Timestamp(System.currentTimeMillis() + (6*60*60*1000));

  private MeetingStatus meetingStatus = MeetingStatus.PENDING;

}
