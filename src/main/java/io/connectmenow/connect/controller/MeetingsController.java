package io.connectmenow.connect.controller;

import io.connectmenow.connect.model.dto.CreateMeetingsDTO;
import io.connectmenow.connect.model.dto.MeetingsDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsDataDTO;
import io.connectmenow.connect.model.dto.UpdateMeetingsParticipantsDTO;
import io.connectmenow.connect.model.dto.UpdateUserCoordinatesDTO;
import io.connectmenow.connect.model.dto.UserCoordinatesDTO;
import io.connectmenow.connect.model.dto.UserParticipantDTO;
import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import io.connectmenow.connect.services.MeetingsService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

  @GetMapping(value = "/{meetingId}/participants", produces = MediaType.APPLICATION_JSON_VALUE)
  public Set<UserParticipantDTO> getMeetingParticipants(
      @PathVariable("meetingId") Long meetingId) {

    return meetingsService.getMeetingParticipants(meetingId);

  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO createMeeting(
      @RequestBody CreateMeetingsDTO createMeetingsDTO) {

    return meetingsService.createMeeting(createMeetingsDTO);

  }

  @PatchMapping(value = "/{meetingId}/{userId}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserParticipantDTO acceptMeeting(
      @PathVariable("userId") Long userId, @PathVariable("meetingId") Long meetingId) {

    return meetingsService.acceptMeeting(userId, meetingId);

  }

  @GetMapping(value = "/{meetingId}/participants/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserCoordinatesDTO getUserCoordinates(
      @PathVariable("userId") Long userId, @PathVariable("meetingId") Long meetingId) {

    return meetingsService.getUserCoordinates(userId, meetingId);

  }

  @PutMapping(value = "/{meetingId}/participants/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserCoordinatesDTO updateUserCoordinates(
      @PathVariable("userId") Long userId, @PathVariable("meetingId") Long meetingId,
      @RequestBody UpdateUserCoordinatesDTO updateUserCoordinatesDTO) {

    return meetingsService.updateUserCoordinates(userId, meetingId, updateUserCoordinatesDTO);

  }

  @PatchMapping(value = "/{meetingId}/{userId}/reject", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserParticipantDTO rejectMeeting(
      @PathVariable("userId") Long userId, @PathVariable("meetingId") Long meetingId) {

    return meetingsService.rejectMeeting(userId, meetingId);

  }

  @PatchMapping(value = "/{meetingId}/complete", produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO completeMeeting(
      @PathVariable("meetingId") Long meetingId) {

    return meetingsService.completeMeeting(meetingId);

  }

  @PatchMapping(value = "/{meetingId}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO cancelMeeting(
      @PathVariable("meetingId") Long meetingId, Long userId) {

    return meetingsService.cancelMeeting(meetingId, userId);

  }

  @PutMapping(value = "/{meetingId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO updateMeetingData(
      @PathVariable("meetingId") Long meetingId,
      @RequestBody UpdateMeetingsDataDTO updateMeetingsDataDTO) {

    return meetingsService.updateMeetingData(meetingId, updateMeetingsDataDTO);

  }

  @PatchMapping(value = "/{meetingId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public MeetingsDTO updateMeetingParticipants(
      @PathVariable("meetingId") Long meetingId,
      @RequestBody UpdateMeetingsParticipantsDTO updateMeetingsParticipantsDTO) {

    return meetingsService.updateMeetingParticipants(meetingId, updateMeetingsParticipantsDTO);

  }

}
