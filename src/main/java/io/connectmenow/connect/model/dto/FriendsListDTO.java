package io.connectmenow.connect.model.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendsListDTO {

  private Set<Long> confirmedFriendsIds = new HashSet<>();

  private Set<Long> incomingRequests = new HashSet<>();

  private Set<Long> outcomingRequests = new HashSet<>();

}
