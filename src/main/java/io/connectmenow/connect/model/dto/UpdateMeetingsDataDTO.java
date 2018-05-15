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
public class UpdateMeetingsDataDTO {

  @NotNull
  private Long id;

  private String title;

  private Double coordinateX;

  private Double coordinateY;

  private MeetingStatus meetingStatus;

}
