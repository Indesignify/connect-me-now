package io.connectmenow.connect.controller;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UpdateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.services.UserService;
import io.connectmenow.connect.services.converters.UserConverter;
import java.util.List;
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
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserConverter userConverter;

  // GET /users?page&pageSize&<filters>
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserDTO> listAllUsers(
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

  // POST /users
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO createUser(
      @Valid @RequestBody CreateUserDTO createUserDTO) {

    if (userService.isUserExist(userConverter.convertCreateUserDTOToUserDTO(createUserDTO))) {
      return null;
    }

    return userService.createUser(createUserDTO);

  }

  // GET /users/{userId}
  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO getUser(
      @PathVariable("userId") Long userId) {

    return userService.getUserById(userId);

  }

  // POST /users/
  @PostMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO updateUser(
      @PathVariable("userId") Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {

    return userService.updateUser(userId, updateUserDTO);

  }

  // PATCH /users/ for partial update
  @PatchMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO updateUserPartially(
      @PathVariable("userId") Long userId, @RequestBody UpdateUserDTO updateUserDTO) {

    return userService.updateUserPartially(userId, updateUserDTO);

  }

  // DELETE /users/
  @DeleteMapping(value = "/{userId}")
  public void deleteUserById(
      @PathVariable("userId") Long userId) {

    userService.deleteUserById(userId);

  }


}
