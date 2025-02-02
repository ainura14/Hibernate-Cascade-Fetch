package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "posts")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
    @SequenceGenerator(name ="post_gen", sequenceName = "post_seq", allocationSize = 1)
    private Long id;
    private String image;
    @Column(name = "descriptions", length = 500)
    private String description;
    private LocalDate createdAt;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post(String image, String description, LocalDate createdAt, User user) {
        this.image = image;
        this.description = description;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Post(String image, String description, LocalDate createdAt) {
        this.image = image;
        this.description = description;
        this.createdAt = createdAt;
    }

    @PrePersist @PreUpdate
    private void setValue(){
        this.createdAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Post{" +
               "id=" + id +
               ", image='" + image + '\'' +
               ", description='" + description + '\'' +
               ", createdAt=" + createdAt;
    }
}
