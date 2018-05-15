package io.connectmenow.connect.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_meetings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"user", "meeting"})
public class MeetingParticipantEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "meeting_id", insertable = false, updatable = false)
  private Long meetingId;

  @ManyToOne
  @JoinColumn(name = "meeting_id", referencedColumnName = "id")
  private MeetingsEntity meeting;

  @Column(name = "user_id", insertable = false, updatable = false)
  private Long userId;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UsersEntity user;

  @Enumerated(EnumType.STRING)
  private ParticipationStatus participationStatus;

  @Column
  private Double userCoordinateX;

  @Column
  private Double userCoordinateY;

}
