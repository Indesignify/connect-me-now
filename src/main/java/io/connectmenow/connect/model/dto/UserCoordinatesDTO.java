package io.connectmenow.connect.model.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCoordinatesDTO {

  @NotNull
  private long userId;

  private long meetingId;

  private Double userCoordinateX;

  private Double userCoordinateY;

}
