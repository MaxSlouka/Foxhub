package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.User;

public interface UserService {
    User findById(Long id);
}
