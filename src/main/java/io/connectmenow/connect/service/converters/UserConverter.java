package io.connectmenow.connect.service.converters;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserConverter {

  public UserDTO convertUsersEntityToUserDTO(UsersEntity usersEntity);
  public List<UsersEntity> convertUsersEntityToUserDTOList(List<UserDTO> userDTO);
  public UsersEntity convertUserDTOToUsersEntity(UserDTO userDTO);
  public List<UserDTO> convertUserDTOToUsersEntityList(List<UsersEntity> usersEntity);

  public UsersEntity convertCreateUserDTOToUsersEntity(CreateUserDTO createUserDTO);
  public CreateUserDTO convertUsersEntityToCreateUserDTO(UsersEntity usersEntity);
}
