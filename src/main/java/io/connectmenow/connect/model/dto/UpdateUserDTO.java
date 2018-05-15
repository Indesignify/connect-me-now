package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.UserStatus;
import java.util.Set;
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
public class UpdateUserDTO {

  @NotNull
  private Long id;

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

  private UserStatus status;

}
