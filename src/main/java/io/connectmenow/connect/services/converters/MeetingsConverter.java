package io.connectmenow.connect.services.converters;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDataDTO;
import io.connectmenow.connect.model.dto.UpdateUserCoordinatesDTO;
import io.connectmenow.connect.model.dto.UserCoordinatesDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import io.connectmenow.connect.model.entities.MeetingsEntity;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.MeetingParticipantRepository;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import org.aspectj.apache.bcel.Repository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = UserConverter.class)
@Component
public interface MeetingsConverter {

  UserConverter userConverter = Mappers.getMapper(UserConverter.class);

  UpdateUserCoordinatesDTO convert(UserCoordinatesDTO userCoordinatesDTO);
  UserCoordinatesDTO convert(UpdateUserCoordinatesDTO updateUserCoordinatesDTO);

  // MeetingsEntity <-> MeetingsDTO
  MeetingsDTO convertMeetingsEntityToMeetingsDTO(MeetingsEntity meetingsEntity);

  List<MeetingsDTO> convertMeetingsEntityToMeetingsDTOList(List<MeetingsEntity> meetingsEntityList);

  // MeetingsEntity <-> CreateMeetingsDTO
  MeetingsEntity convertCreateMeetingsDTOToMeetingsEntity(CreateMeetingsDTO createMeetingsDTO);

  CreateMeetingsDTO convertMeetingsEntityToCreateMeetingsDTO(MeetingsEntity meetingsEntity);

  // MeetingsEntity <-> UpdateMeetingsDataDTO
  MeetingsEntity convertUpdateMeetingsDataDTOToMeetingsEntity(
      UpdateMeetingsDataDTO updateMeetingsDataDTO);

  UpdateMeetingsDataDTO convertMeetingsEntityToUpdateMeetingsDataDTO(MeetingsEntity meetingsEntity);

  MeetingParticipantEntity convertUserParticipantDTOToMeetingParticipantEntity(
      UserParticipantDTO userParticipantDTO);

  default Set<UserParticipantDTO> convert(
      Set<MeetingParticipantEntity> meetingParticipantEntities) {
    Set<UserParticipantDTO> userDTOSet = new LinkedHashSet<>();
    meetingParticipantEntities.forEach(entity -> userDTOSet.add(convert(entity)));
    return userDTOSet;
  }

  default UserParticipantDTO convert(MeetingParticipantEntity meetingParticipantEntity) {
    UsersEntity usersEntity = meetingParticipantEntity.getUser();

    UserParticipantDTO userParticipantDTO =
        userConverter.convertUsersEntityToUserParticipantDTO(usersEntity);

    userParticipantDTO.setParticipationStatus(meetingParticipantEntity.getParticipationStatus());
    userParticipantDTO.setUserCoordinateX(meetingParticipantEntity.getUserCoordinateX());
    userParticipantDTO.setUserCoordinateY(meetingParticipantEntity.getUserCoordinateY());

    return userParticipantDTO;
  }

}
