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

}
