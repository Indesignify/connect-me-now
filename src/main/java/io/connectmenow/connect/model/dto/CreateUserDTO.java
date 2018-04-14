package io.connectmenow.connect.model.dto;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

  @Size(min=2, max=40)
  private String firstName;

  @Size(min=2, max=40)
  private String lastName;

  @Size(min=3, max=128)
  private String nickname;

  @Size(min=6, max=64)
  private String password;

  private String email;

  private String avatar;

}
