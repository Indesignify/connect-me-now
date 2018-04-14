package io.connectmenow.connect.model.entities;

import java.sql.Timestamp;
import java.util.List;
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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_friends",
        joinColumns=@JoinColumn(name="personId"),
        inverseJoinColumns=@JoinColumn(name="friendId")
    )
    private List<UsersEntity> friends;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="users_friends",
        joinColumns=@JoinColumn(name="friendId"),
        inverseJoinColumns=@JoinColumn(name="personId")
    )
    private List<UsersEntity> friendOf;

}
