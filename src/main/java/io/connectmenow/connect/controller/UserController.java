package io.connectmenow.connect.controller;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

  // GET /users/
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserDTO> listAllUsers() {
    List<UserDTO> users = userService.getAllUsers();

    if (users.isEmpty()) {
      return null;
    }

    return users;
  }

  // GET /users/{userId}
  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO getUser(
      @PathVariable("userId") Long userId) {

    return userService.getUserById(userId);
  }

  // GET /users
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public List<UserDTO> getUsers(
//      @PathParam("firstName") String firstName,
//      @PathParam("lastName") String lastName) {
//
//    return userService.findUsers(firstName, lastName);
//  }

  // GET /users?{filter}  <=> /users?lastName=...&firstName=...&...
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public List<UserDTO> searchUser(
//      @RequestParam(value = "search") String search) {
//
//    return null;
//  }

  // POST /users -> CreateUserDTO
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void createUser (
      @RequestBody CreateUserDTO createUserDTO) {

    userService.saveUser(createUserDTO);
  }

  // PUT /users/ -> UpdateUserDTO


  // DELETE /users/ -> Set inactive status


  // ---------------------
  // POST /users/friends/{userId}  <=> posts json array of [ id_1, id_2, ..., id_n ]


  // DELETE /users/friends/{userId}


  // GET /users/friends    <=> returns json array (even if there is 1 friend)


}
