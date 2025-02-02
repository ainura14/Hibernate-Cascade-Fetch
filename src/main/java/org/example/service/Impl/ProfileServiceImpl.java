package org.example.service.Impl;

import org.example.dao.Impl.ProfileDaoImpl;
import org.example.dao.Impl.UserDaoImpl;
import org.example.dao.ProfileDao;
import org.example.dao.UserDao;
import org.example.entities.Profile;
import org.example.service.ProfileService;

import java.util.NoSuchElementException;

public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao = new ProfileDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void saveProfile(Long userId, Profile profile) {
        try{
            userDao.findByID(userId)
                    .orElseThrow(() -> new NoSuchElementException("Can't find user with id: " + userId));
            profileDao.saveProfile(userId, profile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Profile saved!");
    }

    @Override
    public void findProfileByUserId(Long userId) {
        userDao.findByID(userId)
                .orElseThrow(() -> new RuntimeException("Can't find user in server with ID: " + userId));
        try {
            System.out.println(profileDao.findProfileByUserId(userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateUserProfile(Long userId, Profile profile) {
        userDao.findByID(userId).orElseThrow(() ->
                new NoSuchElementException("Can't find user id: " + userId + " in service!"));
        try {
            return profileDao.updateUserProfile(userId, profile);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public void deleteProfileByUserId(Long id) {
        try {
            userDao.deletedByID(id);
        } catch (Exception e) {
            System.out.println(e.getMessage() );
        }
    }
}
