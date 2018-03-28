package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.UserStatus;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

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
}
