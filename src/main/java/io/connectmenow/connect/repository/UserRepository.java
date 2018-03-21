package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UsersEntity, Long> {

}
