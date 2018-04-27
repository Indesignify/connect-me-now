package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.UpdateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserConverter {

  // UsersEntity <-> UserDTO
  UsersEntity convertUserDTOToUsersEntity(UserDTO userDTO);
  List<UsersEntity> convertUserDTOToUsersEntityList(List<UserDTO> userDTOs);
  UserDTO convertUsersEntityToUserDTO(UsersEntity usersEntity);

  List<UserDTO> convertUsersEntityToUserDTOList(List<UsersEntity> usersEntities);

  // UsersEntity <-> CreateUserDTO
  UsersEntity convertCreateUserDTOToUsersEntity(CreateUserDTO createUserDTO);
  CreateUserDTO convertUsersEntityToCreateUserDTO(UsersEntity usersEntity);

  // UsersEntity <-> UpdateUserDTO
  UsersEntity convertUpdateUserDTOToUsersEntity(UpdateUserDTO updateUserDTO);
  UpdateUserDTO convertUsersEntityToUpdateUserDTO(UsersEntity usersEntity);

  // UserDTO <-> UpdateUserDTO
  UserDTO convertUpdateUserDTOToUserDTO(UpdateUserDTO updateUserDTO);
  UpdateUserDTO convertUserDTOToUpdateUserDTO(UserDTO userDTO);

  // UserDTO <-> CreateUserDTO
  UserDTO convertCreateUserDTOToUserDTO(CreateUserDTO createUserDTO);
  CreateUserDTO convertUserDTOToCreateUserDTO(UserDTO userDTO);

  // UsersEntity <-> UserParticipantDTO
  UserParticipantDTO convertUsersEntityToUserParticipantDTO(UsersEntity usersEntity);

}
