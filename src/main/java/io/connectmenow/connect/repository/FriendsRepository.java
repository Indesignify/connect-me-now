package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.FriendsEntity;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface FriendsRepository extends CrudRepository<FriendsEntity, Long> {

  FriendsEntity findFriendsEntityByFriendId(Long friendId);

  FriendsEntity findFriendsEntityByPersonId(Long userId);

  FriendsEntity findFriendsEntitiesByPersonIdAndFriendId(Long personId, Long friendId);

}
