package io.connectmenow.connect.controller;

import io.connectmenow.connect.model.dto.FriendsDTO;
import io.connectmenow.connect.services.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{userId}/friends")
public class FriendsController {

  @Autowired
  FriendsService friendsService;

  @PostMapping(value = "/{friendId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public FriendsDTO addFriend(
      @PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {

    return friendsService.requestFriendship(userId, friendId);

  }

  @PatchMapping(value = "/{friendId}/accept", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public FriendsDTO acceptFriendship(
      @PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {

    return friendsService.acceptFriendship(userId, friendId);

  }

  @DeleteMapping(value = "/{friendId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public FriendsDTO deleteFriend(
      @PathVariable("userId") Long userId, @PathVariable("friendId") Long friendToDeleteId) {

    return friendsService.deleteFriend(userId, friendToDeleteId);

  }

  @PatchMapping(value = "/{friendId}/reject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public FriendsDTO rejectFriendship(
      @PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {

    return friendsService.rejectFriendship(userId, friendId);

  }

}
