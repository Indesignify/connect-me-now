package io.connectmenow.connect.model.dto;

import javax.validation.constraints.NotNull;
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

  @NotNull
  @Size(min=2, max=40)
  private String firstName;

  @NotNull
  @Size(min=2, max=40)
  private String lastName;

  @NotNull
  @Size(min=3, max=128)
  private String nickname;

  @NotNull
  @Size(min=6, max=64)
  private String password;

  private String email;

  private String avatar;

}
