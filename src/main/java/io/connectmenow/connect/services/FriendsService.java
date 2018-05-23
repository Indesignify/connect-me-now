package io.connectmenow.connect.services;

import io.connectmenow.connect.model.dto.FriendsDTO;

public interface FriendsService {

  FriendsDTO requestFriendship(Long userId, Long friendId);

  FriendsDTO acceptFriendship(Long responderId, Long requesterId);

  FriendsDTO deleteFriend(Long deleteRequesterId, Long friendToDeleteId);

  FriendsDTO rejectFriendship(Long responderId, Long requesterId);

}
