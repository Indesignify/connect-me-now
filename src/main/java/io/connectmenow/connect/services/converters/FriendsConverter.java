package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.FriendsDTO;
import io.connectmenow.connect.model.entities.FriendsEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FriendsConverter {

  FriendsEntity convert(FriendsDTO friendsDTO);
  FriendsDTO convert(FriendsEntity friendsEntity);

}
