package com.restaurantbooking.rest_reserv.repository;

import com.restaurantbooking.rest_reserv.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUsername(String username);
}
