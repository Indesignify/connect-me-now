package io.connectmenow.connect.services.impl;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDTO;
import io.connectmenow.connect.model.entities.MeetingStatus;
import io.connectmenow.connect.model.entities.MeetingsEntity;
import io.connectmenow.connect.repository.MeetingsRepository;
import io.connectmenow.connect.services.MeetingsService;
import io.connectmenow.connect.services.converters.MeetingsConverter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("meetingsService")
@Transactional
public class MeetingsServiceImpl implements MeetingsService {

  @Autowired
  MeetingsRepository meetingsRepository;

  @Autowired
  MeetingsConverter meetingsConverter;

  @Override
  public MeetingsDTO getMeetingById(Long meetingId) {

    final MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).orElse(null);

    if (meetingsEntity == null) {
      return null;
    }

    return meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity);

  }

  @Override
  public List<MeetingsDTO> getAllMeetings() {

    List<MeetingsDTO> allMeetingsList = new ArrayList<>();

    meetingsRepository.findAll().forEach(meetingsEntity ->
        allMeetingsList.add(meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity)));

    return allMeetingsList;

  }

  @Override
  public MeetingsDTO createMeeting(CreateMeetingsDTO createMeetingsDTO) {

    MeetingsDTO newMeeting = meetingsConverter
          .convertMeetingsEntityToMeetingsDTO(meetingsRepository
              .save(meetingsConverter
                  .convertCreateMeetingsDTOToMeetingsEntity(createMeetingsDTO)));

    return newMeeting;

  }

  @Override
  public MeetingsDTO updateMeeting(Long meetingId, UpdateMeetingsDTO updateMeetingsDTO) {

    updateMeetingsDTO.setMeetingId(meetingId);

    MeetingsEntity meetingsEntityToUpdate = meetingsConverter
            .convertUpdateMeetingsDTOToMeetingsEntity(updateMeetingsDTO);

    MeetingsDTO updatedMeetingsDTO = meetingsConverter
        .convertMeetingsEntityToMeetingsDTO(meetingsEntityToUpdate);

    return updatedMeetingsDTO;

  }

  @Override
  public MeetingsDTO cancelMeeting(Long meetingId) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).orElse(null);

    meetingsEntity.setMeetingStatus(MeetingStatus.CANCELLED);

    meetingsEntity.setEndedAt(new Timestamp(System.currentTimeMillis()));

    return meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity);

  }

  @Override
  public MeetingsDTO completeMeeting(Long meetingId) {

    MeetingsEntity meetingsEntity = meetingsRepository.findById(meetingId).orElse(null);

    meetingsEntity.setMeetingStatus(MeetingStatus.SUCCESSFUL);

    meetingsEntity.setEndedAt(new Timestamp(System.currentTimeMillis()));

    return meetingsConverter.convertMeetingsEntityToMeetingsDTO(meetingsEntity);

  }
}
