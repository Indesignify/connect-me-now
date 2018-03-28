package io.connectmenow.connect.service;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import java.util.List;


public interface UserService {

  UserDTO getUserById(Long id);
  UserDTO createUser(CreateUserDTO userDTO);

  List<UserDTO> findUsers(String firstName, String lastName);
}
