package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserConverter {

  public UserDTO convertUsersEntityToUserDTO(UsersEntity usersEntity);
  public List<UserDTO> convertUsersEntityToUserDTOList(List<UsersEntity> usersEntities);
  public UsersEntity convertUserDTOToUsersEntity(UserDTO userDTO);
  public List<UsersEntity> convertUserDTOToUsersEntityList(List<UserDTO> userDTOs);

  public UsersEntity convertCreateUserDTOToUsersEntity(CreateUserDTO createUserDTO);
  public List<UsersEntity> convertCreateUserDTOToUsersEntityList(List<CreateUserDTO> createUserDTOS);
  public CreateUserDTO convertUsersEntityToCreateUserDTO(UsersEntity usersEntity);
  public List<CreateUserDTO> convertUsersEntityToCreateUserDTOList(List<UsersEntity> usersEntities);
}
