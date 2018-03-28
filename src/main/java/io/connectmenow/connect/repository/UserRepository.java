package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UsersEntity, Long> {
  List<UsersEntity> findUserByFirstName(String firstName);
  List<UsersEntity> findUserByLastName(String lastName);
  List<UsersEntity> findUserByNickname(String nickname);
  List<UsersEntity> findUserByEmail(String email);
  List<UsersEntity> findUserByFirstNameAndLastName(String firstName, String lastName);
}
