package org.example.dao;

import org.example.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    void savePost(Long userId, Post post);

    void getPostByUserId(Long userId);

    List<Post> searchPost(String query);

    void assignPostToUser(Long userId, Long postId);

    void deletePostById(Long id);

    Optional<Post> findById(Long postId);
}
