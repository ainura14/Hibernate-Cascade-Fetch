package org.example.dao.Impl;

import jakarta.persistence.EntityManager;
import org.example.confid.DBconfig;
import org.example.dao.PostDao;
import org.example.entities.Post;
import org.example.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDaoImpl implements PostDao {
    private final EntityManager entityManage =
            DBconfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void savePost(Long userId, Post post) {
        try {
            entityManage.getTransaction().begin();
            User user = entityManage.find(User.class, userId);
            post.setUser(user);
            entityManage.persist(post);
            entityManage.getTransaction().commit();
        } catch (Exception e) {
            entityManage.getTransaction().rollback();
            throw new RuntimeException("Can't save post!");
        }
    }

    @Override
    public void getPostByUserId(Long userId) {
        try {
            User user = entityManage.find(User.class, userId);
            List<Post> postList = user.getPostList();
            System.out.println(postList);
        } catch (Exception e) {
            throw new RuntimeException("Can't find post!");
        }
    }

    @Override
    public List<Post> searchPost(String query) {
        return entityManage.createQuery("select p from Post p where p.description like :query", Post.class)
                .setParameter("query", query)
                .getResultList();
    }

    @Override
    public void assignPostToUser(Long userId, Long postId) {
//        try {
//            entityManage.getTransaction().begin();
//            Post post = entityManage.find(Post.class, postId);
//            User user = entityManage.find(User.class, userId);
//            post.setUser(user);
//            if (user.getPostList() == null) {
//                user.setPostList(new ArrayList<>());
//            }
//            user.getPostList().add(post);
//            entityManage.getTransaction().commit();
//        }catch (Exception e){
//            throw new RuntimeException("Can't assign post to user!");
//        }
    }

    @Override
    public void deletePostById(Long id) {
        try {
            entityManage.getTransaction().begin();
            entityManage.remove(entityManage.find(Post.class, id));
            entityManage.getTransaction().commit();
        } catch (Exception e) {
            entityManage.getTransaction().rollback();
            throw new RuntimeException("Can't deleted post!");
        }
    }

    @Override
    public Optional<Post> findById(Long postId) {
        try {
            return Optional.ofNullable(entityManage.find(Post.class, postId));
        } catch (Exception e) {
            throw new RuntimeException("Can't find post by id");
        }
    }
}
