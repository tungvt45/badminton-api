package com.prcbadminton.badminton.repository;

import com.prcbadminton.badminton.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndActive(String email, boolean active);
    List<User> getAllByRoleNotLike(String role);
}
