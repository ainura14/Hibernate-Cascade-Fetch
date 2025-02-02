package org.example.service;

import org.example.entities.User;

public interface UserService {
    void save(User user);
    void deletedByID(Long id);

    User findByID(Long id);
}
