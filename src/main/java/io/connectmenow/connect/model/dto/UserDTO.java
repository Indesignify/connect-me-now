package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.UserStatus;
import java.sql.Timestamp;
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
public class UserDTO {

  @NotNull
  private long id;

  private String firstName;

  private String lastName;

  private String nickname;

  private String email;

  private String avatar;

  private Timestamp registrationDate;

  private Timestamp lastOnline;

  private UserStatus status;

  private Boolean isValidated;

  private Set<MeetingParticipantDTO> meetingsOfUser;

}
