package io.connectmenow.connect.services;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDataDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsParticipantsDTO;
import io.connectmenow.connect.model.dto.UpdateUserCoordinatesDTO;
import io.connectmenow.connect.model.dto.UserCoordinatesDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import java.util.List;
import java.util.Set;

public interface MeetingsService {

  MeetingsDTO getMeetingById(Long meetingId);

  List<MeetingsDTO> getAllMeetings();

  Set<UserParticipantDTO> getMeetingParticipants(Long meetingId);

  UserCoordinatesDTO getUserCoordinates(Long userId, Long meetingId);

  UserCoordinatesDTO updateUserCoordinates(Long userId, Long meetingId, UpdateUserCoordinatesDTO updateUserCoordinatesDTO);

  MeetingsDTO createMeeting(CreateMeetingsDTO createMeetingsDTO);

  MeetingsDTO updateMeetingData(Long meetingId, UpdateMeetingsDataDTO updateMeetingsDataDTO);

  MeetingsDTO updateMeetingParticipants(Long meetingId,
      UpdateMeetingsParticipantsDTO updateMeetingsParticipantsDTO);

  MeetingsDTO cancelMeeting(Long meetingId, Long userId);

  void acceptMeeting(Long userId, Long meetingId);

  void rejectMeeting(Long userId, Long meetingId);

  MeetingsDTO completeMeeting(Long meetingId);

}
