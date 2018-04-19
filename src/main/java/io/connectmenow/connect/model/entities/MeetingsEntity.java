package io.connectmenow.connect.model.entities;

import java.util.Set;
import javax.persistence.*;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "meetings", schema = "public", catalog = "postgres")
public class MeetingsEntity {

  @Id
  @Column(name = "meeting_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long meetingId;

  @Column
  @CreationTimestamp
  private Timestamp createdAt;

  @Column
  private Timestamp endedAt;

  @Column
  private Timestamp expiresAt;

  @Enumerated(EnumType.STRING)
  private MeetingStatus meetingStatus;

// @JoinColumn(name = "initiator_id", referencedColumnName = "meeting_initiator_id")
  @OneToOne(mappedBy = "meetingInitiator", fetch = FetchType.EAGER)
  @JoinColumn(name = "initiator_id", referencedColumnName = "meeting_initiator_id")
  private UsersMeetings meetingInitiator;

  @OneToMany(mappedBy = "meeting", fetch = FetchType.EAGER)
  private Set<UsersMeetings> meetingParticipants;

}
