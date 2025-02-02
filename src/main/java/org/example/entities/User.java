package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.postgresql.shaded.com.ongres.scram.client.ScramClient;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    private String username;
    private String email;
    private String password;
    //multi cascade = ALL
    @OneToOne(cascade = {PERSIST, REMOVE}, fetch = FetchType.EAGER)
    //OneToOne   EAGER
    //ManyToOne  EAGER
    //OneToMany  LAZY //if we want just see one object without references
    //ManyToMany LAZY
    private Profile profile;
    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();
    @ManyToMany
    private List<Comment> comments;

    public User(String username, String email, String password, Profile profile) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
