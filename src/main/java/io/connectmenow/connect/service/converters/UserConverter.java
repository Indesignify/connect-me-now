package io.connectmenow.connect.service.converters;

import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserConverter {

  public UsersEntity convertUsersEntityToUserDTO(UserDTO userDTO);
  public List<UsersEntity> convertUsersEntityToUserDTOList(List<UserDTO> userDTO);
  public UserDTO convertUserDTOToUsersEntity(UsersEntity usersEntity);
  public List<UserDTO> convertUserDTOToUsersEntityList(List<UsersEntity> usersEntity);
}
