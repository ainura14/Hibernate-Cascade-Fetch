package org.example.service;

import org.example.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    void savePost(Long userId, Post post);

    void getPostByUserId(Long userId);

    List<Post> searchPost(String query);

    String assignPostToUser(Long userId, Long postId);

    void deletePostById(Long id);

    Optional<Post> findById(Long postId);
}
