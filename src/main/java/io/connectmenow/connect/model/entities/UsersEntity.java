package io.connectmenow.connect.model.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
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
