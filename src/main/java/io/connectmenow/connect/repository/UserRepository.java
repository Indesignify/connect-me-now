package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.UsersEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UsersEntity, Long> {
  List<UsersEntity> findUserByFirstName(String firstName);
  List<UsersEntity> findUserByLastName(String lastName);
  List<UsersEntity> findUserByNickname(String nickname);
  List<UsersEntity> findUserByEmail(String email);
  List<UsersEntity> findUserByFirstNameAndLastName(String firstName, String lastName);

//  public List<UserDTO> findUsers(String firstName, String lastName) {
//
//    List<UsersEntity> users = userRepository
//        .findUserByFirstNameAndLastName(firstName, lastName);
//
//    return userConverter.convertUserDTOToUsersEntityList(users);
//  }


  @Query(value = "SELECT u FROM users WHERE lastName = lastName",
          nativeQuery = true)
  List<UsersEntity> findUsersByParameters(@Param("searchTerm") String searchTerm);
}
