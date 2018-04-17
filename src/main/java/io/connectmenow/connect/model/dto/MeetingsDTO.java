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
public class MeetingsDTO {

  private long meetingId;

  private long requesterId;

  private long responderId;

  private Double requesterCoordX;

  private Double requesterCoordY;

  private Double responderCoordX;

  private Double responderCoordY;

  private Timestamp createdAt;

  private Timestamp endedAt;

  private Timestamp expiresAt;

  private MeetingStatus meetingStatus;

}
