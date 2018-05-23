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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(exclude = {
    "meetingsOfUser", "friends", "friendOf", "updatedAt", "lastOnline", "isValidated"})
@Table(name = "users", schema = "public", catalog = "postgres")
public class UsersEntity {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String nickname;

  @Column
  private String password;

  @Column
  private String email;

  @Column
  private String avatar;

  @Column
  @CreationTimestamp
  private Timestamp registrationDate;

  @Column
  @UpdateTimestamp
  private Timestamp updatedAt;

  @Column
  private Timestamp lastOnline = new Timestamp(System.currentTimeMillis());

  @Column
  private Boolean isValidated = false;

  @Column
  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private Set<MeetingParticipantEntity> meetingsOfUser = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "friendOf")
  private Set<FriendsEntity> friendOfWho = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "friendWith")
  private Set<FriendsEntity> friends = new HashSet<>();

}
