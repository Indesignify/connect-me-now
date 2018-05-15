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
public class UpdateMeetingsParticipantsDTO {

  @NotNull
  private Long id;

  private Set<UserParticipantDTO> newParticipants;

}
