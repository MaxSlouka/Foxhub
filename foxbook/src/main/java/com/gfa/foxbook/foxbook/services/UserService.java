package com.gfa.foxbook.foxbook.services;

import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    void delete(User user);
}
