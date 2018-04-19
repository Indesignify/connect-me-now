package io.connectmenow.connect.model.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_meetings")
public class UsersMeetings implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "meeting_id", referencedColumnName = "meeting_id")
  private MeetingsEntity meeting;

  @OneToOne
  @JoinColumn(name = "meeting_initiator_id", referencedColumnName = "id")
  private UsersEntity meetingInitiator;

  @ManyToOne
  @JoinColumn(name = "meeting_participant_id", referencedColumnName = "id")
  private UsersEntity meetingParticipant;

  @Enumerated(EnumType.STRING)
  private ParticipationStatus participationStatus;

}
