package io.connectmenow.connect.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "meetings", schema = "public", catalog = "postgres")
public class MeetingsEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long meetingId;

    private long requesterId;
    private long responderId;
    private Double requesterCoordX;
    private Double requesterCoordY;
    private Double responderCoordX;
    private Double responderCoordY;
    private Timestamp createdAt;
    private Timestamp endedAt;
    private Timestamp expiresAt;
    private MeetingStatus meetingStatus;

    @Id
    @Column(name = "meeting_id", nullable = false)
    public long getMeetingId() {
        return meetingId;
    }

    @Basic
    @Column(name = "requester_id", nullable = false)
    public long getRequesterId() {
        return requesterId;
    }

    @Basic
    @Column(name = "responder_id", nullable = false)
    public long getResponderId() {
        return responderId;
    }

    @Basic
    @Column(name = "requester_coord_x", nullable = true, precision = 0)
    public Double getRequesterCoordX() {
      return requesterCoordX;
    }

    @Basic
    @Column(name = "requester_coord_y", nullable = true, precision = 0)
    public Double getRequesterCoordY() {
      return requesterCoordY;
    }

    @Basic
    @Column(name = "responder_coord_x", nullable = true, precision = 0)
    public Double getResponderCoordX() {
      return responderCoordX;
    }

    @Basic
    @Column(name = "responder_coord_y", nullable = true, precision = 0)
    public Double getResponderCoordY() {
      return responderCoordY;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Basic
    @Column(name = "ended_at", nullable = true)
    public Timestamp getEndedAt() {
        return endedAt;
    }

    @Basic
    @Column(name = "expires_at", nullable = true)
    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    @Basic
    @Column(name = "meeting_status", nullable = true)
    public MeetingStatus getMeetingStatus() {
        return meetingStatus;
    }
}
