package org.example.service.Impl;

import org.example.dao.CommentDao;
import org.example.dao.Impl.CommentDaoImpl;
import org.example.dao.Impl.PostDaoImpl;
import org.example.dao.Impl.UserDaoImpl;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.entities.Comment;
import org.example.service.CommentService;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao = new CommentDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final PostDao postDao = new PostDaoImpl();

    @Override
    public String saveComment(Long postId, Long userId, Comment comment) {
        userDao.findByID(userId)
                .orElseThrow(
                        () -> new NoSuchElementException("Can't find user with ID: " + userId)
                );
        postDao.findById(postId).orElseThrow(
                () -> new NoSuchElementException("Can't find post with ID: " + postId)
        );
        try {
            return commentDao.saveComment(postId, userId, comment);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Optional<Comment> findCommentByPostId(Long postId) {
        postDao.findById(postId).orElseThrow(
                () -> new NoSuchElementException("Can't find post!")
        );
        return commentDao.findCommentByPostId(postId);
    }

    @Override
    public String updateComment(Long commentId, String newText) {
        return commentDao.updateComment(commentId, newText);
    }

    @Override
    public String deleteComment(Comment comment) {
        commentDao.findByID(comment.getId()).orElseThrow(
                () -> new NoSuchElementException("Can't find comment!")
        );
        return commentDao.deleteComment(comment);
    }
}
