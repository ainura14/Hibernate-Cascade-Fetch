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
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_gen")
    @SequenceGenerator(name = "comment_gen", sequenceName = "comment_seq", allocationSize = 1)
    private Long id;
    private String text;
    private LocalDate commentDate;
    @ManyToMany(mappedBy = "comments", cascade = CascadeType.DETACH)
    private List<User> userList;
    @ManyToOne
    @JoinColumn(name = "post_ID")
    private Post post;

    public Comment(String text, LocalDate commentDate) {
        this.text = text;
        this.commentDate = commentDate;
    }

    public Comment(Long id, String text, LocalDate commentDate) {
        this.id = id;
        this.text = text;
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
               "id=" + id +
               ", text='" + text + '\'' +
               ", commentDate=" + commentDate +
               '}';
    }
}
