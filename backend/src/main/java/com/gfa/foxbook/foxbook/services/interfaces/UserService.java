package com.gfa.foxbook.foxbook.services.interfaces;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.UserBasicDTO;

import java.util.Optional;
import java.util.List;

public interface UserService {
    Optional<User> findById(Long id);

    void delete(User user);

    List<User> getAll();

    Optional<User> findByNickname(String nickname);

    User getByNickname(String nickname);

    User updateProfile(User user, User userDTO);
    User upgradeUser(String nickname);

    Optional<User> findByEmail(String name);

    void addComment(User existingUser, String comment);
    public UserBasicDTO convertToUserBasicDTO(User user);

    List<User> searchUsers(String query);
}
