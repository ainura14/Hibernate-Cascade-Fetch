package org.example.service;

import org.example.dao.ProfileDao;
import org.example.entities.Profile;

public interface ProfileService {
    void saveProfile(Long userID, Profile profile);
    void findProfileByUserId(Long userId);

    String updateUserProfile(Long userId, Profile profile);
    void deleteProfileByUserId(Long id);
}
