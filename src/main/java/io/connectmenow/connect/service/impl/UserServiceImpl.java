package io.connectmenow.connect.service.impl;

import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.UserRepository;
import io.connectmenow.connect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

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
}
