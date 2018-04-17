package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.MeetingStatus;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMeetingsDTO {

  @NotNull
  private Long meetingId;

  private Double requesterCoordX;

  private Double requesterCoordY;

  private Double responderCoordX;

  private Double responderCoordY;

  private MeetingStatus meetingStatus;

}
