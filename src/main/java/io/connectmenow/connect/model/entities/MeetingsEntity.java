//package io.connectmenow.connect.model.entities;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.Objects;
//
//@Entity
//@Table(name = "meetings", schema = "public", catalog = "postgres")
//public class MeetingsEntity {
//    private int meetingId;
//    private int requesterId;
//    private int responderId;
//    private Double requesterCoord;
//    private Double responderCoord;
//    private Timestamp createdAt;
//    private Timestamp endedAt;
//    private Timestamp expiresAt;
//    private String meetingStatus;
//
//    @Id
//    @Column(name = "meeting_id", nullable = false)
//    public int getMeetingId() {
//        return meetingId;
//    }
//
//    public void setMeetingId(int meetingId) {
//        this.meetingId = meetingId;
//    }
//
//    @Basic
//    @Column(name = "requester_id", nullable = false)
//    public int getRequesterId() {
//        return requesterId;
//    }
//
//    public void setRequesterId(int requesterId) {
//        this.requesterId = requesterId;
//    }
//
//    @Basic
//    @Column(name = "responder_id", nullable = false)
//    public int getResponderId() {
//        return responderId;
//    }
//
//    public void setResponderId(int responderId) {
//        this.responderId = responderId;
//    }
//
//    @Basic
//    @Column(name = "requester_coord", nullable = true, precision = 0)
//    public Double getRequesterCoord() {
//        return requesterCoord;
//    }
//
//    public void setRequesterCoord(Double requesterCoord) {
//        this.requesterCoord = requesterCoord;
//    }
//
//    @Basic
//    @Column(name = "responder_coord", nullable = true, precision = 0)
//    public Double getResponderCoord() {
//        return responderCoord;
//    }
//
//    public void setResponderCoord(Double responderCoord) {
//        this.responderCoord = responderCoord;
//    }
//
//    @Basic
//    @Column(name = "created_at", nullable = true)
//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    @Basic
//    @Column(name = "ended_at", nullable = true)
//    public Timestamp getEndedAt() {
//        return endedAt;
//    }
//
//    public void setEndedAt(Timestamp endedAt) {
//        this.endedAt = endedAt;
//    }
//
//    @Basic
//    @Column(name = "expires_at", nullable = true)
//    public Timestamp getExpiresAt() {
//        return expiresAt;
//    }
//
//    public void setExpiresAt(Timestamp expiresAt) {
//        this.expiresAt = expiresAt;
//    }
//
//    @Basic
//    @Column(name = "meeting_status", nullable = true)
//    public String getMeetingStatus() {
//        return meetingStatus;
//    }
//
//    public void setMeetingStatus(String meetingStatus) {
//        this.meetingStatus = meetingStatus;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MeetingsEntity that = (MeetingsEntity) o;
//        return meetingId == that.meetingId &&
//                requesterId == that.requesterId &&
//                responderId == that.responderId &&
//                Objects.equals(requesterCoord, that.requesterCoord) &&
//                Objects.equals(responderCoord, that.responderCoord) &&
//                Objects.equals(createdAt, that.createdAt) &&
//                Objects.equals(endedAt, that.endedAt) &&
//                Objects.equals(expiresAt, that.expiresAt) &&
//                Objects.equals(meetingStatus, that.meetingStatus);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(meetingId, requesterId, responderId, requesterCoord, responderCoord, createdAt, endedAt, expiresAt, meetingStatus);
//    }
//}
