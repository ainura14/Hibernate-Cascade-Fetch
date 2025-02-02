package org.example.dao.Impl;

import jakarta.persistence.EntityManager;
import org.example.confid.DBconfig;
import org.example.dao.ProfileDao;
import org.example.entities.Profile;
import org.example.entities.User;

import java.util.Optional;

public class ProfileDaoImpl implements ProfileDao {
    private final EntityManager entityManager =
            DBconfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void saveProfile(Long userID, Profile profile) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(profile);
            User user = entityManager.find(User.class, userID);
            user.setProfile(profile);
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Can't save this profile!");
        }
    }

    @Override
    public Optional<Profile> findProfileByUserId(Long userId) {
        try{
            User user = entityManager.find(User.class, userId);
            Profile profile = user.getProfile();
            if(profile == null){
                return Optional.empty();
            }
            return Optional.of(profile);
        }catch (Exception e){
            throw new RuntimeException("Can't find profile with user in dao id: " + userId);
        }
    }

    @Override
    public String updateUserProfile(Long userId, Profile profile) {
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, userId);
            Profile profile1 = user.getProfile();
            if(profile1 == null){
                throw new RuntimeException("Can't find profile!");
            }
            profile1.setFullName(profile.getFullName());
            profile1.setBiography(profile.getBiography());
            profile1.setGender(profile.getGender());
            profile1.setDateOfBirth(profile.getDateOfBirth());
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Can't find profile!");
        }
    return "Successfully updated!";
    }

    @Override
    public void deleteProfileByUserId(Long id) {
        //detach
        try {
            entityManager.getTransaction().begin();
            // first method
//            User user = entityManager.createQuery("""
//                    select u from User u where u.profile.id = :profileID
//                    """, User.class).setParameter("profileID", id).getSingleResult();
//            user.setProfile(null);
//            entityManager.merge(user);

            //second method
            entityManager.createQuery("""
                            update User u
                            set u.profile = null
                            where u.profile.id = ?1
                            """)
                    .setParameter(1, id)
                    .executeUpdate();
            entityManager.remove(
                    entityManager.find(Profile.class, id)
            );
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Can't delete profile with id: " + id);
        }
    }
}
