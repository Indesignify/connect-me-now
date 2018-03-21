//package io.connectmenow.connect.model.entities;
//
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.util.Objects;
//
//@Entity
//@Table(name = "users_friends", schema = "public", catalog = "postgres")
//public class UsersFriendsEntity {
//    private int userId;
//    private int friendId;
//
//    @Basic
//    @Column(name = "user_id", nullable = false)
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    @Basic
//    @Column(name = "friend_id", nullable = false)
//    public int getFriendId() {
//        return friendId;
//    }
//
//    public void setFriendId(int friendId) {
//        this.friendId = friendId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UsersFriendsEntity that = (UsersFriendsEntity) o;
//        return userId == that.userId &&
//                friendId == that.friendId;
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(userId, friendId);
//    }
//}
