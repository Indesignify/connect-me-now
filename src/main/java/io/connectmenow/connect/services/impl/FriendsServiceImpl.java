package io.connectmenow.connect.services.impl;

import io.connectmenow.connect.model.dto.FriendsDTO;
import io.connectmenow.connect.model.entities.FriendsEntity;
import io.connectmenow.connect.model.entities.FriendshipStatus;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.FriendsRepository;
import io.connectmenow.connect.repository.UserRepository;
import io.connectmenow.connect.services.FriendsService;
import io.connectmenow.connect.services.converters.FriendsConverter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("friendsService")
@Transactional
public class FriendsServiceImpl implements FriendsService {

  @Autowired
  FriendsRepository friendsRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  FriendsConverter friendsConverter;

  @Override
  public FriendsDTO requestFriendship(Long userId, Long friendId) {

    if (userId == friendId) {
      throw new IllegalArgumentException("User with id " + userId + " can't become a friend of himself!");
    }

    if (friendsRepository
        .findFriendsEntitiesByPersonIdAndFriendIdAndFriendshipStatus(userId, friendId, FriendshipStatus.PENDING) != null) {
      throw new IllegalArgumentException("Friendship request from " + userId + " "
          + "to " + friendId + " already exists!");
    }

    if (friendsRepository
        .findFriendsEntitiesByPersonIdAndFriendIdAndFriendshipStatus(friendId, userId, FriendshipStatus.PENDING) != null) {
      throw new IllegalArgumentException("Mirror friendship request from " + friendId + " "
          + "to " + userId + " already exists! Please reject or accept it first.");
    }

    if (areFriends(userId, friendId)) {
      throw new IllegalArgumentException("User with id " + userId + " is already a"
          + " friend of user with id " + friendId + "!");
    }

    UsersEntity requester = userRepository.findById(userId).get();

    UsersEntity responder = userRepository.findById(friendId).get();

    FriendsEntity friendsEntity = FriendsEntity
        .builder()
        .personId(userId)
        .friendOf(requester)
        .friendId(friendId)
        .friendWith(responder)
        .friendshipStatus(FriendshipStatus.PENDING)
        .build();

    friendsRepository.save(friendsEntity);

    FriendsDTO friendsDTO = friendsConverter.convert(friendsEntity);

    return friendsDTO;

  }

  @Override
  public FriendsDTO acceptFriendship(Long responderId, Long requesterId) {

    if (responderId == requesterId) {
      throw new IllegalArgumentException("User with id " + responderId + " can't accept friendship of himself!");
    }

    FriendsEntity requesterFriendsEntity = friendsRepository
        .findFriendsEntityByPersonIdAndFriendIdAndFriendshipStatus(requesterId, responderId, FriendshipStatus.PENDING);

    if (requesterFriendsEntity == null) {
      throw new IllegalArgumentException("Friendship request from user with id " + requesterId + " to user with id " +
         responderId + " not found!");
    }

    if (areFriends(responderId, requesterId)) {
      throw new IllegalArgumentException("User with id " + responderId + " is already a "
          + "friend of user with id " + requesterId + "!");
    }

    UsersEntity requester = userRepository.findById(responderId).get();

    UsersEntity responder = userRepository.findById(requesterId).get();

    requesterFriendsEntity.setFriendshipStatus(FriendshipStatus.ACCEPTED);

    friendsRepository.save(requesterFriendsEntity);

    FriendsEntity responderFriendsEntity = FriendsEntity
        .builder()
        .personId(requesterId)
        .friendOf(requester)
        .friendId(responderId)
        .friendWith(responder)
        .friendshipStatus(FriendshipStatus.ACCEPTED)
        .build();

    friendsRepository.save(responderFriendsEntity);

    FriendsDTO newFriend = friendsConverter.convert(responderFriendsEntity);

    return newFriend;

  }

  @Override
  public FriendsDTO deleteFriend(Long deleteRequesterId, Long friendToDeleteId) {

    if (deleteRequesterId == friendToDeleteId) {
      throw new IllegalArgumentException("User with id " + deleteRequesterId + " can't delete himself from friends!");
    }

    if (!areFriends(deleteRequesterId, friendToDeleteId)) {
      throw new IllegalArgumentException("User with id " + deleteRequesterId + " is not a friend of" +
            " user with id " + friendToDeleteId + "!");
    }

    FriendsEntity deleteRequesterEntity = friendsRepository
        .findFriendsEntityByPersonIdAndFriendIdAndFriendshipStatus(deleteRequesterId, friendToDeleteId, FriendshipStatus.ACCEPTED);

    FriendsEntity friendsEntityToDelete = friendsRepository
        .findFriendsEntityByPersonIdAndFriendIdAndFriendshipStatus(friendToDeleteId, deleteRequesterId, FriendshipStatus.ACCEPTED);

    deleteRequesterEntity.setFriendshipStatus(FriendshipStatus.PENDING);

    friendsRepository.save(deleteRequesterEntity);
    friendsRepository.delete(friendsEntityToDelete);

    FriendsDTO deleteRequesterDTO = friendsConverter.convert(deleteRequesterEntity);

    return deleteRequesterDTO;

  }

  @Override
  public FriendsDTO rejectFriendship(Long responderId, Long requesterId) {

    if (responderId == requesterId) {
      throw new IllegalArgumentException("User with id " + responderId + " can't reject friendship of himself!");
    }

    FriendsEntity requesterFriendsEntity = friendsRepository
        .findFriendsEntityByPersonIdAndFriendIdAndFriendshipStatus(requesterId, responderId, FriendshipStatus.PENDING);

    if (requesterFriendsEntity == null) {
      throw new IllegalArgumentException("Pending friendship request from user with id " + requesterId
          + " to user with id " + responderId + " not found!");
    }

    if (areFriends(responderId, requesterId)) {
      throw new IllegalArgumentException("User with id " + responderId + " is already a "
          + "friend of user with id " + requesterId + ", can't reject friendship."
          + "Please use deleteFriend instead.");
    }

    requesterFriendsEntity.setFriendshipStatus(FriendshipStatus.REJECTED);

    friendsRepository.save(requesterFriendsEntity);

    FriendsDTO friendsDTO = friendsConverter.convert(requesterFriendsEntity);

    return friendsDTO;

  }

  @Override
  public Boolean areFriends(Long userId, Long friendId) {

    FriendsEntity friendsEntity = friendsRepository
        .findFriendsEntitiesByPersonIdAndFriendIdAndFriendshipStatus(userId, friendId, FriendshipStatus.ACCEPTED);

    return friendsEntity != null;

  }

}
