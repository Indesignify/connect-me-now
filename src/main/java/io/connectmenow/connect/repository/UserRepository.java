package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<UsersEntity, Long> {

  @Query(value = "SELECT u FROM UsersEntity u WHERE "
      + "(:firstName IS NULL OR u.firstName = :firstName) AND "
      + "(:lastName IS NULL OR u.lastName = :lastName) AND "
      + "(:nickname IS NULL OR u.nickname = :nickname) AND "
      + "(:email IS NULL OR u.email = :email)")
  List<UsersEntity> findUsersByParams(
      @Param("firstName") String firstName, @Param("lastName") String lastName,
      @Param("nickname") String nickname, @Param("email") String email);

}
