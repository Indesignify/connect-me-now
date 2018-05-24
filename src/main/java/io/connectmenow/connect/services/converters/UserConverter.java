package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.FriendsListDTO;
import io.connectmenow.connect.model.dto.UpdateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.FriendsEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.FriendsRepository;
import io.connectmenow.connect.services.MeetingsService;
import io.connectmenow.connect.services.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = MeetingParticipantConverter.class)
@Component
public abstract class UserConverter {

  @Autowired
  FriendsRepository friendsRepository;

  @Autowired
  FriendsConverter friendsConverter;

  @Autowired
  MeetingParticipantConverter meetingParticipantConverter;

  @Autowired
  UserService userService;

  // UsersEntity <-> UserDTO
  public abstract UsersEntity convertUserDTOToUsersEntity(UserDTO userDTO);

  public abstract List<UsersEntity> convertUserDTOToUsersEntityList(List<UserDTO> userDTOs);

  public UserDTO convertUsersEntityToUserDTO(UsersEntity usersEntity) {

    UserDTO userDTO = new UserDTO();

    if (usersEntity.getId() != null) {
      userDTO.setId(usersEntity.getId());
    }
    userDTO.setFirstName(usersEntity.getFirstName());
    userDTO.setLastName(usersEntity.getLastName());
    userDTO.setNickname(usersEntity.getNickname());
    userDTO.setEmail(usersEntity.getEmail());
    userDTO.setAvatar(usersEntity.getAvatar());
    userDTO.setRegistrationDate(usersEntity.getRegistrationDate());
    userDTO.setLastOnline(usersEntity.getLastOnline());
    userDTO.setStatus(usersEntity.getStatus());
    userDTO.setIsValidated(usersEntity.getIsValidated());
    userDTO.setMeetingsOfUser(meetingParticipantConverter
        .convertMeetingParticipantEntitySetToMeetingParticipantDTOSet(
            usersEntity.getMeetingsOfUser()));
    userDTO.setFriendsOfUser(userService.getUserFriendsById(usersEntity.getId()));

    return userDTO;

  }

  public abstract List<UserDTO> convertUsersEntityToUserDTOList(List<UsersEntity> usersEntities);

  // UsersEntity <-> CreateUserDTO
  public abstract UsersEntity convertCreateUserDTOToUsersEntity(CreateUserDTO createUserDTO);

  public abstract CreateUserDTO convertUsersEntityToCreateUserDTO(UsersEntity usersEntity);

  // UsersEntity <-> UpdateUserDTO
  public abstract UsersEntity convertUpdateUserDTOToUsersEntity(UpdateUserDTO updateUserDTO);

  public abstract UpdateUserDTO convertUsersEntityToUpdateUserDTO(UsersEntity usersEntity);

  // UserDTO <-> UpdateUserDTO
  public abstract UserDTO convertUpdateUserDTOToUserDTO(UpdateUserDTO updateUserDTO);

  public abstract UpdateUserDTO convertUserDTOToUpdateUserDTO(UserDTO userDTO);

  // UserDTO <-> CreateUserDTO
  public abstract UserDTO convertCreateUserDTOToUserDTO(CreateUserDTO createUserDTO);

  public abstract CreateUserDTO convertUserDTOToCreateUserDTO(UserDTO userDTO);

  // UsersEntity <-> UserParticipantDTO
  public abstract UserParticipantDTO convertUsersEntityToUserParticipantDTO(
      UsersEntity usersEntity);

}
