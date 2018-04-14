package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<UsersEntity, Long> {

  @Query(value = "SELECT u FROM users u WHERE "
      + "(LOWER(u.first_name) = LOWER(:firstName) OR :firstName IS NULL) AND "
      + "(LOWER(u.last_name) = LOWER(:lastName) OR :lastName IS NULL) AND "
      + "(LOWER(u.nickname) = LOWER(:nickname) OR :nickname IS NULL) AND "
      + "(LOWER(u.email) = LOWER(:email) OR :email IS NULL)",
      nativeQuery = true)
  List<UsersEntity> findUsersByParams(
      @Param("firstName") String firstName, @Param("lastName") String lastName,
      @Param("nickname") String nickname, @Param("email") String email);

}
