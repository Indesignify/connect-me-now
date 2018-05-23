package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.FriendsListDTO;
import io.connectmenow.connect.model.dto.UpdateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.FriendsEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.FriendsRepository;
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

  MeetingParticipantConverter meetingParticipantConverter
      = Mappers.getMapper(MeetingParticipantConverter.class);

  // UsersEntity <-> UserDTO
  public abstract UsersEntity convertUserDTOToUsersEntity(UserDTO userDTO);
  public abstract List<UsersEntity> convertUserDTOToUsersEntityList(List<UserDTO> userDTOs);
  public abstract UserDTO convertUsersEntityToUserDTO(UsersEntity usersEntity);

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
  public abstract UserParticipantDTO convertUsersEntityToUserParticipantDTO(UsersEntity usersEntity);

//  public FriendsListDTO convertFriendsEntitySetToFriendsListDTO(Set<FriendsEntity> friendsEntities) {
//    Set<Long> friendsEntitiesIdsSet = new HashSet<>();
//
//    friendsEntities.forEach(frEnt ->
//        friendsEntitiesIdsSet.add(frEnt.getId()));
//
//    Set<FriendsEntity> confirmedFriendsIds = friendsRepository.findFriendsEntitiesByFriendOfAndFriendWith();
//
//    Set<Long> incomingRequests;
//
//    Set<Long> outcomingRequests;
//  }

}
