package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Role;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CommentService commentService;

    @Override
    public Optional<User> findById(Long id) {
        assert userRepository != null;
        return userRepository.findById(id);
    }

    @Override
    public void delete(User user) {
        assert userRepository != null;
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        assert userRepository != null;
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User updateProfile(User user) {
        assert userRepository != null;
        return userRepository.save(user);
    }

    public Optional<User> upgradeUser(String nickname) {
        Optional<User> maybeUser = userRepository.findByNickname(nickname);

        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            List<Role> roles = new ArrayList<>();
            Role adminRole = new Role("ADMIN");
            roles.add(adminRole);

            user.setRoles(roles);

            User updatedUser = userRepository.save(user);
            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String accessProfileUrl(User user) {
        return "/profile/" + user.getNickname();
    }


    @Override
    public Optional<User> findByEmail(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    public void addComment(User existingUser, String comment) {
        commentService.comment(existingUser.getId(), existingUser.getId(), comment);
        userRepository.save(existingUser);

    }
}
