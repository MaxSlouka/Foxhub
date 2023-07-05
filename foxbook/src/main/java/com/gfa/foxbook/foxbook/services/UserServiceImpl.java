package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User updateProfile(User user) {
        return userRepository.save(user);
    }

    @Override
    public String accessProfileUrl(User user) {
        return user.getProfileUrl();
    }
}
