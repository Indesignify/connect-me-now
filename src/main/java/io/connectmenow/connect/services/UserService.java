package io.connectmenow.connect.services;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.FriendsListDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.entities.FriendsEntity;
import java.util.List;
import java.util.Set;


public interface UserService {

  UserDTO getUserById(Long id);

  Set<MeetingsDTO> getUserMeetingsById(Long userId);

  FriendsListDTO getUserFriendsById(Long userId);

  List<UserDTO> getAllUsers(String firstName, String lastName, String nickname, String email);

  UserDTO createUser(CreateUserDTO createUserDTO);

  UserDTO updateUser(Long userId, UpdateUserDTO updateUserDTO);

  UserDTO updateUserPartially(Long userId, UpdateUserDTO updateUserDTO);

  void deleteUserById(Long userId);

  void deleteAllUsers();

  boolean isUserExist(UserDTO userDTO);

}
