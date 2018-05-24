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

    FriendsEntity requesterFriendsEntity = friendsRepository.findFriendsEntityByPersonId(requesterId);

    if (requesterFriendsEntity == null) {
      throw new IllegalArgumentException("Friend request from user " + requesterId + " not found!");
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

    FriendsEntity deleteRequesterEntity = friendsRepository
        .findFriendsEntitiesByPersonIdAndFriendId(deleteRequesterId, friendToDeleteId);

    FriendsEntity friendsEntityToDelete = friendsRepository
        .findFriendsEntitiesByPersonIdAndFriendId(friendToDeleteId, deleteRequesterId);

    deleteRequesterEntity.setFriendshipStatus(FriendshipStatus.PENDING);

    friendsRepository.save(deleteRequesterEntity);
    friendsRepository.delete(friendsEntityToDelete);

    FriendsDTO deleteRequesterDTO = friendsConverter.convert(deleteRequesterEntity);

    return deleteRequesterDTO;

  }

  @Override
  public FriendsDTO rejectFriendship(Long responderId, Long requesterId) {

    FriendsEntity requesterFriendsEntity = friendsRepository.findFriendsEntityByPersonId(requesterId);

    if (requesterFriendsEntity == null) {
      throw new IllegalArgumentException("Friend request from user " + requesterId + " not found!");
    }

    requesterFriendsEntity.setFriendshipStatus(FriendshipStatus.REJECTED);

    friendsRepository.save(requesterFriendsEntity);

    FriendsDTO friendsDTO = friendsConverter.convert(requesterFriendsEntity);

    return friendsDTO;

  }

}
