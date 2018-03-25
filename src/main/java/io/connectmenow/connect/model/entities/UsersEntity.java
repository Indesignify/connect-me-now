package io.connectmenow.connect.model.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import org.springframework.util.CollectionUtils;

@Builder
@NoArgsConstructor
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
    private Status status;
    private Boolean isValidated;

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

    public UsersEntity(
        long id,
        String firstName,
        String lastName,
        String nickname,
        String password,
        String email,
        String avatar,
        Timestamp registrationDate,
        Timestamp lastOnline,
        Status status,
        Boolean isValidated,
        List<UsersEntity> friends,
        List<UsersEntity> friendOf) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.lastOnline = lastOnline;
        this.status = status;
        this.isValidated = isValidated;
        this.friends = (friends != null) ? friends : new ArrayList<>();
        this.friendOf = (friendOf != null) ? friendOf : new ArrayList<>();
    }

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }
}
