package io.connectmenow.connect.model.dto;

import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingParticipantDTO {

  @NotNull
  private Long meetingId;

  private String title;

  private Set<UserMeetingInfoDTO> participants;

}
