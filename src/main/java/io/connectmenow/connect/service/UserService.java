package io.connectmenow.connect.service;

import io.connectmenow.connect.model.dto.UserDTO;

public interface UserService {

  UserDTO getUserById(Long id);
}
