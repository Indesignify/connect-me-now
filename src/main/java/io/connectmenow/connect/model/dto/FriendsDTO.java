package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.FriendshipStatus;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendsDTO {

  @NotNull
  private Long id;

  private Long personId;

  private Long friendId;

  private FriendshipStatus friendshipStatus;

}
