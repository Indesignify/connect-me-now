package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.MeetingStatus;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingsDTO {

  private Long id;

  private Timestamp createdAt;

  private Timestamp endedAt;

  private Timestamp expiresAt;

  private MeetingStatus meetingStatus;

  private Double coordinateX;

  private Double coordinateY;

  private List<UserParticipantDTO> meetingParticipants;

}
