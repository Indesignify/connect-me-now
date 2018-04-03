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

    @Enumerated(EnumType.STRING)
    private MeetingStatus meetingStatus;

}
