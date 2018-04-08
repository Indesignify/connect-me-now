package io.connectmenow.connect.services;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import java.util.List;


public interface UserService {

  UserDTO getUserById(Long id);

  void saveUser(CreateUserDTO createUserDTO);

  void updateUser(UserDTO userDTO);

  void deleteUserById(Long userId);

  List<UserDTO> getAllUsers();

  void deleteAllUsers();

  public boolean isUserExist(UserDTO userDTO);

  // List<UserDTO> findUsers(String firstName, String lastName);
}
