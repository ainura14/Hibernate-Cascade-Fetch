package org.example.dao;

import org.example.entities.Comment;

import java.util.Optional;

public interface CommentDao {
    String saveComment(Long postId, Long userId, Comment comment);
    Optional<Comment> findCommentByPostId(Long postId);
    String updateComment(Long commentId, String newText);
    String deleteComment(Comment comment);

    Optional<Comment> findByID(Long id);
}
