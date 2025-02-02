package org.example.service.Impl;

import org.example.dao.Impl.PostDaoImpl;
import org.example.dao.Impl.UserDaoImpl;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.entities.Post;
import org.example.entities.User;
import org.example.service.PostService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PostServiceImpl implements PostService {
    private final PostDao postDao = new PostDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void savePost(Long userId, Post post) {
        userDao.findByID(userId).orElseThrow(() ->
                new NoSuchElementException("Can't find user with ID: " + userId));
        try {
            postDao.savePost(userId, post);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getPostByUserId(Long userId) {
        userDao.findByID(userId).orElseThrow(() ->
                new NoSuchElementException("Can't find user!"));
        try {
            postDao.getPostByUserId(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Post> searchPost(String query) {
        return postDao.searchPost(query);
    }

    @Override
    public String assignPostToUser(Long userId, Long postId) {
        try {
            User user = userDao.findByID(userId).orElse(null);
            Post post = postDao.findById(postId).orElse(null);
            if(user == null){
                return "User with id: " + userId + " not found!";
            }else if(post == null){
                return "Post with id: " + postId + " not found!";
            }
            postDao.assignPostToUser(userId, postId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Post assigned to user successfully!";
    }

    @Override
    public void deletePostById(Long id) {
        postDao.findById(id);
        try {
            postDao.deletePostById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Successfully deleted!");
    }

    @Override
    public Optional<Post> findById(Long postId) {
        try {
            return postDao.findById(postId);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
