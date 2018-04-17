package io.connectmenow.connect.services;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDTO;
import java.util.List;

public interface MeetingsService {

  MeetingsDTO getMeetingById(Long meetingId);

  List<MeetingsDTO> getAllMeetings();

  MeetingsDTO createMeeting(CreateMeetingsDTO createMeetingsDTO);

  MeetingsDTO updateMeeting(Long meetingId, UpdateMeetingsDTO updateMeetingsDTO);

  MeetingsDTO cancelMeeting(Long meetingId);

  MeetingsDTO completeMeeting(Long meetingId);

}
