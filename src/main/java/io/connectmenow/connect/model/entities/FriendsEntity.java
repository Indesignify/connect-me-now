//package io.connectmenow.connect.model.entities;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name = "friends", schema = "public", catalog = "postgres")
//public class FriendsEntity {
//    private int id;
//    private String nickname;
//
//    @Id
//    @Column(name = "id", nullable = false)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "nickname", nullable = false, length = 80)
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FriendsEntity that = (FriendsEntity) o;
//        return id == that.id &&
//                Objects.equals(nickname, that.nickname);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(id, nickname);
//    }
//}
