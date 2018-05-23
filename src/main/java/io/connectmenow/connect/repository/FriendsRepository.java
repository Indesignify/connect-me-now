package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.FriendsEntity;
import io.connectmenow.connect.model.entities.FriendshipStatus;
import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FriendsRepository extends CrudRepository<FriendsEntity, Long> {

  FriendsEntity findFriendsEntityByFriendId(Long friendId);

  FriendsEntity findFriendsEntityByPersonId(Long userId);

  FriendsEntity findFriendsEntitiesByPersonIdAndFriendId(Long personId, Long friendId);

  @Query(value = "SELECT f FROM FriendsEntity f WHERE "
      + "f.friendId = :friendId AND f.friendshipStatus = :friendshipStatus")
  Set<FriendsEntity> findFriendsEntitiesByFriendOf(
      @Param("friendId") Long userId, @Param("friendshipStatus") FriendshipStatus friendshipStatus);

  @Query(value = "SELECT f FROM FriendsEntity f WHERE "
      + "f.personId = :personId AND f.friendshipStatus = :friendshipStatus")
  Set<FriendsEntity> findFriendsEntitiesByFriends(
      @Param("personId") Long userId, @Param("friendshipStatus") FriendshipStatus friendshipStatus);

}
