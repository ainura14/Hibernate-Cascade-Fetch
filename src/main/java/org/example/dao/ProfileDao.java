package org.example.dao;

import org.example.entities.Profile;

import java.util.Optional;

public interface ProfileDao {
    void saveProfile(Long userID, Profile profile);
    Optional<Profile> findProfileByUserId (Long userId);

    String updateUserProfile(Long userId, Profile profile);
    void deleteProfileByUserId(Long id);
}
