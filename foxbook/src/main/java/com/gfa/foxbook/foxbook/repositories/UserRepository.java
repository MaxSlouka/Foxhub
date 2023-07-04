package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gfa.foxbook.foxbook.models.User;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
