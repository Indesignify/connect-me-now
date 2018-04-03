package io.connectmenow.connect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

  private String firstName;
  private String lastName;
  private String nickname;
  private String email;
  private String password;
}
