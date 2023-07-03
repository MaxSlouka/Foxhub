package com.gfa.foxbook.foxbook.services;

import org.springframework.security.core.userdetails.User;

public interface UserService {
    User findById(Long id);
}
