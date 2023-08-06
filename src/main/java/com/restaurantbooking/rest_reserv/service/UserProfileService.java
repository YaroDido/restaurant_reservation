package com.restaurantbooking.rest_reserv.service;

import com.restaurantbooking.rest_reserv.entity.UserProfile;
import com.restaurantbooking.rest_reserv.exception.NotFoundException;
import com.restaurantbooking.rest_reserv.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> getUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }
    public void updateUserProfile(Long id, UserProfile updatedProfile) {
        UserProfile existingProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UserProfile not found"));

        existingProfile.setUsername(updatedProfile.getUsername());
        existingProfile.setEmail(updatedProfile.getEmail());
        existingProfile.setPassword(updatedProfile.getPassword());

        userProfileRepository.save(existingProfile);
    }

    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
}
