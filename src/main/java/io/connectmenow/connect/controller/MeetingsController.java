package io.connectmenow.connect.controller;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDTO;
import io.connectmenow.connect.services.MeetingsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {

  @Autowired
  MeetingsService meetingsService;

  @GetMapping(value = "/{meetingId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO getMeetingDataById(
      @PathVariable("meetingId") Long meetingId) {

    return meetingsService.getMeetingById(meetingId);

  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<MeetingsDTO> getAllMeetings() {

    return meetingsService.getAllMeetings();

  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO createMeeting(
      @RequestBody CreateMeetingsDTO createMeetingsDTO) {

    return meetingsService.createMeeting(createMeetingsDTO);

  }

  @PostMapping(value = "/{meetingId}/complete", produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO completeMeeting(
      @PathVariable("meetingId") Long meetingId) {

    return meetingsService.completeMeeting(meetingId);

  }

  @PostMapping(value = "/{meetingId}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO cancelMeeting(
      @PathVariable("meetingId") Long meetingId) {

    return meetingsService.cancelMeeting(meetingId);

  }

  @PutMapping(value = "/{meetingId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO updateMeetingData(
      @PathVariable("meetingId") Long meetingId,
      @RequestBody UpdateMeetingsDTO updateMeetingsDTO) {

    return meetingsService.updateMeeting(meetingId, updateMeetingsDTO);

  }

}
