package io.connectmenow.connect.model.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
public class UsersEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String nickname;

    private String password;

    private String email;

    private String avatar;

    private Timestamp registrationDate;

    private Timestamp lastOnline;

    private Boolean isValidated;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToMany
    @JoinTable(name="users_friends",
        joinColumns=@JoinColumn(name="personId"),
        inverseJoinColumns=@JoinColumn(name="friendId")
    )
    private List<UsersEntity> friends;

    @ManyToMany
    @JoinTable(name="users_friends",
        joinColumns=@JoinColumn(name="friendId"),
        inverseJoinColumns=@JoinColumn(name="personId")
    )
    private List<UsersEntity> friendOf;
}
