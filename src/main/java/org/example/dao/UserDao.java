package org.example.dao;

import org.example.entities.Profile;
import org.example.entities.User;

import java.util.Optional;

public interface UserDao {
    void save(User user);
    Optional<User> findByID(Long userId);
    void deletedByID(Long id);
}
