package com.gfa.foxbook.foxbook.services;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();
}
