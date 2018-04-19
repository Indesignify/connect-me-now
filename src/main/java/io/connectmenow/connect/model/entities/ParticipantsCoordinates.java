package io.connectmenow.connect.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participants_coordinates")
public class ParticipantsCoordinates {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long participantsCoordinatesId;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "meeting_id")
  private MeetingsEntity meeting;

  @Column
  private Long participantId;

  @Column(name = "coordinate_x")
  private Double coordinateX;

  @Column(name = "coordinate_y")
  private Double coordinateY;

}
