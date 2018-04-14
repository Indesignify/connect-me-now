package io.connectmenow.connect.services;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UpdateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import java.util.List;


public interface UserService {

  UserDTO getUserById(Long id);

  List<UserDTO> getAllUsers(String firstName, String lastName, String nickname, String email);

  UserDTO createUser(CreateUserDTO createUserDTO);

  UserDTO updateUser(Long userId, UpdateUserDTO updateUserDTO);

  UserDTO updateUserPartially(Long userId, UpdateUserDTO updateUserDTO);

  void deleteUserById(Long userId);

  void deleteAllUsers();

  boolean isUserExist(UserDTO userDTO);

}
