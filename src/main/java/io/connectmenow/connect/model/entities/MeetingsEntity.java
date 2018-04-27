package io.connectmenow.connect.model.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode
@Table(name = "meetings", schema = "public", catalog = "postgres")
public class MeetingsEntity {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @CreationTimestamp
  private Timestamp createdAt;

  @Column
  private Timestamp endedAt;

  @Column
  private Timestamp expiresAt;

  @Enumerated(EnumType.STRING)
  private MeetingStatus meetingStatus;

  @Column
  private Double coordinateX;

  @Column
  private Double coordinateY;

//  @OneToOne(mappedBy = "meetingInitiator", fetch = FetchType.EAGER)
//  @JoinColumn(name = "initiator_id", referencedColumnName = "meeting_initiator_id")
//  private MeetingParticipantEntity meetingInitiator;

  @OneToMany(mappedBy = "meeting", fetch = FetchType.EAGER)
  private Set<MeetingParticipantEntity> meetingParticipants = new HashSet<>();

}
