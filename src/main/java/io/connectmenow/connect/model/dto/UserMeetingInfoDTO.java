package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.MeetingsEntity;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMeetingInfoDTO {

  @NotNull
  private long id;

  private String firstName;

  private String lastName;

  private String nickname;

}
