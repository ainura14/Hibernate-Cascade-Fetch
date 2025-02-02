package org.example.service.Impl;

import org.example.dao.Impl.UserDaoImpl;
import org.example.dao.UserDao;
import org.example.entities.User;
import org.example.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void save(User user) {
        try {
            userDao.save(user);
            System.out.println("Successfully saved user!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletedByID(Long id) {
        try {
            userDao.deletedByID(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User findByID(Long id){
        return userDao.findByID(id).orElse(null);
    }
}
