package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Role;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

    @Override
    public String accessProfileUrl(User user) {
        return user.getProfileUrl();
    }

    public Optional<User> upgradeUser(String nickname){
        Optional<User> maybeUser = userRepository.findByNickname(nickname);
        if (maybeUser.isEmpty()){
            return maybeUser;
        } else {
//            maybeUser.get().setRoles(ne);
//            return ;
        }
        return null;
    }
}
