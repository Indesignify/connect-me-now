package io.connectmenow.connect.controller;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.services.UserService;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserDTO> getUsersByParams(
      @RequestParam(value="firstName", required=false) String firstName,
      @RequestParam(value="lastName", required=false) String lastName,
      @RequestParam(value="nickname", required=false) String nickname,
      @RequestParam(value="email", required=false) String email) {

    List<UserDTO> users = userService.getAllUsers(firstName, lastName, nickname, email);

    if (users.isEmpty()) {
      return null;
    }

    return users;

  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO createUser(
      @Valid @RequestBody CreateUserDTO createUserDTO) {

    return userService.createUser(createUserDTO);

  }

  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO getUser(
      @PathVariable("userId") Long userId) {

    return userService.getUserById(userId);

  }

  @GetMapping(value = "/{userId}/meetings", produces = MediaType.APPLICATION_JSON_VALUE)
  public Set<MeetingsDTO> getUserMeetings(
      @PathVariable("userId") Long userId) {

    return userService.getUserMeetingsById(userId);

  }

  @PostMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO updateUser(
      @PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {

    return userService.updateUser(userId, updateUserDTO);

  }

  @PatchMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO updateUserPartially(
      @PathVariable("userId") Long userId, @RequestBody UpdateUserDTO updateUserDTO) {

    return userService.updateUserPartially(userId, updateUserDTO);

  }

  @DeleteMapping(value = "/{userId}")
  public void deleteUser(
      @PathVariable("userId") Long userId) {

    userService.deleteUserById(userId);

  }


}
