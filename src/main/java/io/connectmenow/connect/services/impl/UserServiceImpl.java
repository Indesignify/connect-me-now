package io.connectmenow.connect.services.impl;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.dto.FriendsListDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateUserDTO;
import io.connectmenow.connect.model.dto.UserDTO;
import io.connectmenow.connect.model.entities.FriendsEntity;
import io.connectmenow.connect.model.entities.FriendshipStatus;
import io.connectmenow.connect.model.entities.UserStatus;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.FriendsRepository;
import io.connectmenow.connect.repository.UserRepository;
import io.connectmenow.connect.services.UserService;
import io.connectmenow.connect.services.converters.FriendsConverter;
import io.connectmenow.connect.services.converters.MeetingParticipantConverter;
import io.connectmenow.connect.services.converters.UserConverter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

  @Autowired
  private MeetingParticipantConverter meetingParticipantConverter;

  @Autowired
  private FriendsConverter friendsConverter;

  @Autowired
  private FriendsRepository friendsRepository;

  @Override
  public UserDTO getUserById(final Long id) {

    UsersEntity usersEntity = userRepository.findById(id).orElse(null);

    if (usersEntity == null) {
      return null;
    }

    return userConverter.convertUsersEntityToUserDTO(usersEntity);

  }

  @Override
  public Set<MeetingsDTO> getUserMeetingsById(Long userId) {

    Set<MeetingsDTO> meetingsOfUser = new HashSet<>();

    UsersEntity usersEntity = userRepository.findById(userId).get();

    meetingsOfUser.addAll(meetingParticipantConverter
        .convertMeetingParticipantEntitySetToMeetingsDTOSet(usersEntity.getMeetingsOfUser()));

    return meetingsOfUser;

  }

  @Override
  public FriendsListDTO getUserFriendsById(Long userId) {

    FriendsListDTO friendsOfUser = new FriendsListDTO();

    Set<FriendsEntity> friendOfEntities = friendsRepository.findFriendsEntitiesByFriendOf(userId, FriendshipStatus.PENDING);

    friendOfEntities.forEach(frOfEnt ->
        friendsOfUser.getIncomingRequests().add((frOfEnt.getPersonId()))
    );

    Set<FriendsEntity> friendsEntities = friendsRepository.findFriendsEntitiesByFriends(userId, FriendshipStatus.PENDING);

    friendsEntities.forEach(frEnt ->
        friendsOfUser.getOutcomingRequests().add((frEnt.getFriendId()))
    );

    Set<FriendsEntity> confirmedFriendsEntities = friendsRepository.findFriendsEntitiesByFriends(userId, FriendshipStatus.ACCEPTED);

    confirmedFriendsEntities.forEach(confFrEnt ->
        friendsOfUser.getConfirmedFriendsIds().add((confFrEnt.getFriendId()))
    );

    return friendsOfUser;

  }

  @Override
  public List<UserDTO> getAllUsers(
      String firstName, String lastName, String nickname, String email) {

    List<UserDTO> allUsers = new ArrayList<>();

    userRepository
        .findUsersByParams(firstName, lastName, nickname, email)
        .forEach(usersEntity ->
            allUsers.add(userConverter.convertUsersEntityToUserDTO(usersEntity)));

    return allUsers;

  }

  @Override
  public UserDTO createUser(CreateUserDTO createUserDTO) {

    UserDTO newUser = userConverter
                      .convertUsersEntityToUserDTO(userRepository
                          .save(userConverter
                              .convertCreateUserDTOToUsersEntity(createUserDTO)));

    return newUser;

  }

  @Override
  public UserDTO updateUser(Long userId, UpdateUserDTO updateUserDTO) {

    updateUserDTO.setId(userId);

    UsersEntity oldUsersEntity = userRepository.findById(userId).get();

    UsersEntity updatedUsersEntity = userConverter.convertUpdateUserDTOToUsersEntity(updateUserDTO);

    updatedUsersEntity.setRegistrationDate(oldUsersEntity.getRegistrationDate());
    updatedUsersEntity.setMeetingsOfUser(oldUsersEntity.getMeetingsOfUser());

    userRepository.save(updatedUsersEntity);

    UserDTO updatedUserDTO = userConverter.convertUsersEntityToUserDTO(updatedUsersEntity);

    return updatedUserDTO;

  }

  @Override
  public UserDTO updateUserPartially(Long userId, UpdateUserDTO updateUserDTO) {

    updateUserDTO.setId(userId);

    UsersEntity usersEntityToUpdate = userRepository.findById(userId).orElse(null);

    if (usersEntityToUpdate == null) {
      return null;
    }

    if (updateUserDTO.getFirstName() != null) {
      usersEntityToUpdate.setFirstName(updateUserDTO.getFirstName());
    }

    if (updateUserDTO.getLastName() != null) {
      usersEntityToUpdate.setLastName(updateUserDTO.getLastName());
    }

    if (updateUserDTO.getNickname() != null) {
      usersEntityToUpdate.setNickname(updateUserDTO.getNickname());
    }

    if (updateUserDTO.getPassword() != null) {
      usersEntityToUpdate.setPassword(updateUserDTO.getPassword());
    }

    if (updateUserDTO.getAvatar() != null) {
      usersEntityToUpdate.setAvatar(updateUserDTO.getAvatar());
    }

    if (updateUserDTO.getStatus() != null) {
      usersEntityToUpdate.setStatus(updateUserDTO.getStatus());
    }

    return userConverter.convertUsersEntityToUserDTO(usersEntityToUpdate);

  }

  @Override
  public void deleteUserById(Long userId) {

    UsersEntity usersEntity = userRepository.findById(userId).orElse(null);

    usersEntity.setStatus(UserStatus.INACTIVE);

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
