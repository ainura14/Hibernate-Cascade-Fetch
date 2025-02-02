package org.example.dao.Impl;

import jakarta.persistence.EntityManager;
import org.example.confid.DBconfig;
import org.example.dao.UserDao;
import org.example.entities.Profile;
import org.example.entities.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager =
            DBconfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Can't saved user!");
        }
    }

    @Override
    public void deletedByID(Long id) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            if(user != null){
                entityManager.remove(user);
            }else{
                throw new RuntimeException("User with id: " + id + " not found!");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Can't deleted user with id: " + id);
        }
    }

    @Override
    public Optional<User> findByID(Long userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }
}
