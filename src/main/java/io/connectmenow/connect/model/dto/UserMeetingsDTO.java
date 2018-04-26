package io.connectmenow.connect.model.dto;

import io.connectmenow.connect.model.entities.MeetingsEntity;
import java.util.List;
import javax.validation.constraints.NotNull;

public class UserMeetingsDTO {

  @NotNull
  private long id;

  private String firstName;

  private String lastName;

  private String nickname; 

  private List<MeetingsEntity> meetingsOfUser;

}
