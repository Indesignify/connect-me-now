package io.connectmenow.connect.service.impl;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.UserRepository;
import io.connectmenow.connect.service.UserService;
import io.connectmenow.connect.service.converters.UserConverter;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserConverter userConverter;

  @Override
  public UserDTO getUserById(final Long id) {

    final UsersEntity usersEntity = userRepository.findById(id).orElse(null);

    if (usersEntity == null) {
      return null;
    }

    return userConverter.convertUsersEntityToUserDTO(usersEntity);

  }

  @Override
  public void saveUser(CreateUserDTO createUserDTO) {

    userRepository.save(userConverter.convertCreateUserDTOToUsersEntity(createUserDTO));

  }

  @Override
  public void updateUser(UserDTO userDTO) {

  }

  @Override
  public void deleteUserById(Long userId) {

    userRepository.deleteById(userId);

  }

  @Override
  public List<UserDTO> getAllUsers() {

//    return userRepository
//        .findAll()
//        .forEach(usersEntity -> userConverter.convertUsersEntityToUserDTO(usersEntity));
    return null;
  }

  @Override
  public void deleteAllUsers() {
    userRepository.deleteAll();
  }

  @Override
  public boolean isUserExist(UserDTO userDTO) {

    return userRepository.existsById(userDTO.getId());

  }

}
