package io.connectmenow.connect.model.entities;

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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(exclude = {
    "friendOf", "friendWith", "actualFriends", "personId", "friendId"})
@Table(name = "users_friends", schema = "public", catalog = "postgres")
public class FriendsEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "person_id", insertable = false, updatable = false)
  private Long personId;

  @ManyToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private UsersEntity friendOf;

  @Column(name = "friend_id", insertable = false, updatable = false)
  private Long friendId;

  @ManyToOne
  @JoinColumn(name = "friend_id", referencedColumnName = "id")
  private UsersEntity friendWith;

//  @ManyToOne
//  private UsersEntity actualFriends;

  @Enumerated(EnumType.STRING)
  private FriendshipStatus friendshipStatus;

}
