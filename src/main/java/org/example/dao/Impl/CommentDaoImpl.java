package org.example.dao.Impl;

import jakarta.persistence.EntityManager;
import org.example.confid.DBconfig;
import org.example.dao.CommentDao;
import org.example.dao.UserDao;
import org.example.entities.Comment;
import org.example.entities.Post;
import org.example.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CommentDaoImpl implements CommentDao {
    private final EntityManager entityManager =
            DBconfig.getEntityManagerFactory().createEntityManager();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public String saveComment(Long postId, Long userId, Comment comment) {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, userId);
            user.getComments().add(comment);
            Post post1 = entityManager.find(Post.class, postId);
            post1.getComments().add(comment);
            comment.setPost(post1);
            entityManager.persist(comment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e.getMessage());
        }
        return "Successfully saved!";
    }

    @Override
    public Optional<Comment> findCommentByPostId(Long postId) {
        try {
            Post post = entityManager.find(Post.class, postId);
            List<Comment> comments = post.getComments();
            return comments.stream().findFirst();
        } catch (Exception e) {
            throw new RuntimeException("Can't find any comment!");
        }
    }

    @Override
    public String updateComment(Long commentId, String newText) {
        try {
            entityManager.getTransaction().begin();
            Comment comment = entityManager.find(Comment.class, commentId);
            comment.setText(newText);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Can't update comment!");
        }
        return "Successfully updated!";
    }

    @Override
    public String deleteComment(Comment comment) {
        try {
            entityManager.getTransaction().begin();
            Comment comment1 = entityManager.find(Comment.class, comment.getId());
            List<User> userList = comment1.getUserList();
            userList.stream()
                    .filter(user -> user.getComments().stream()
                            .anyMatch(comm -> comm.getId().equals(comment.getId())))
                    .findFirst()
                    .ifPresent(user ->{
                        user.setComments(null);
                    });
            Post post = comment1.getPost();
            post.setComments(null);
            entityManager.remove(comment1);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new NoSuchElementException("Can't find comment!");
        }
        return "Successfully deleted!";
    }

    @Override
    public Optional<Comment> findByID(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }
}
