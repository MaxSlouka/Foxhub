package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.User;

import java.util.Optional;
import java.util.List;

public interface UserService {
    Optional<User> findById(Long id);

    void delete(User user);

    List<User> findAll();

    Optional<User> findByNickname(String nickname);

    User updateProfile(User user);

    String accessProfileUrl(User user);

    Optional<User> findByEmail(String name);

    void addComment(User existingUser, String comment);
}
