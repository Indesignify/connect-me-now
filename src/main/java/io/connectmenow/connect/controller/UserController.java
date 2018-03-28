package io.connectmenow.connect.controller;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.service.UserService;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO getUser(
      @PathVariable("userId") Long userId) {

    return userService.getUserById(userId);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserDTO> getUsers(
      @PathParam("firstName") String firstName,
      @PathParam("lastName") String lastName) {

    return userService.findUsers(firstName, lastName);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO createUser (
      @RequestBody CreateUserDTO userDTO) {

    return null;
  }
}
