package io.connectmenow.connect.model.entities;

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
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long meetingId;

    @Column
    private long requesterId;

    @Column
    private long responderId;

    @Column
    private Double requesterCoordX;

    @Column
    private Double requesterCoordY;

    @Column
    private Double responderCoordX;

    @Column
    private Double responderCoordY;

    @Column
    @CreationTimestamp
    private Timestamp createdAt;

    @Column
    private Timestamp endedAt;

    @Column
    private Timestamp expiresAt;

    @Enumerated(EnumType.STRING)
    private MeetingStatus meetingStatus;

}
