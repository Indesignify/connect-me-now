package io.connectmenow.connect.services;

import io.connectmenow.connect.model.dto.FriendsDTO;

public interface FriendsService {

  FriendsDTO requestFriendship(Long userId, Long friendId);

}
