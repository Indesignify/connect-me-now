package io.connectmenow.connect.service.impl;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.UserRepository;
import io.connectmenow.connect.service.UserService;
import io.connectmenow.connect.service.converters.UserConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserConverter userConverter;

  @Override
  public UserDTO getUserById(final Long id) {

    final UsersEntity usersEntity = userRepository.findById(id).orElse(null);

    if(usersEntity == null) {
      return null;
    }

    // For easier transformation entity <-> dto it is useful to use tools. For example http://mapstruct.org/
    return UserDTO.builder()
        .id(usersEntity.getId())
        .firstName(usersEntity.getFirstName())
        .lastName(usersEntity.getLastName())
        .email(usersEntity.getEmail())
        // and so on...
        .build();
  }

  @Override
  public UserDTO createUser(CreateUserDTO userDTO) {

    // final UsersEntity usersEntity = userRepository.save;

    return null;
  }

  @Override
  public List<UserDTO> findUsers(String firstName, String lastName) {

    List<UsersEntity> users = userRepository
        .findUserByFirstNameAndLastName(firstName, lastName);

    return userConverter.convertUserDTOToUsersEntityList(users);
  }


}
