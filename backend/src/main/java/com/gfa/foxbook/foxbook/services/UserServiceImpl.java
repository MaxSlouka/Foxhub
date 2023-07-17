package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.dtos.UserBasicDTO;
import com.gfa.foxbook.foxbook.models.nonusermodels.Role;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import com.gfa.foxbook.foxbook.services.interfaces.CommentService;
import com.gfa.foxbook.foxbook.services.interfaces.UserService;
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
    public List<User> getAll() {
        assert userRepository != null;
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User getByNickname(String nickname) {
        return userRepository.getByNickname(nickname);
    }

    @Override
    public User updateProfile(User user, User userDTO) {
        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        } else {
            user.setFirstName(user.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        } else {
            user.setLastName(user.getLastName());
        }
        if (userDTO.getCompleteProjects() != null) {
            user.setCompleteProjects(userDTO.getCompleteProjects());
        } else {
            user.setCompleteProjects(user.getCompleteProjects());
        }
        if (userDTO.getYearsOfExperience() != null) {
            user.setYearsOfExperience(userDTO.getYearsOfExperience());
        } else {
            user.setYearsOfExperience(user.getYearsOfExperience());
        }
        if (userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        } else {
            user.setPhone(user.getPhone());
        }
        if (userDTO.getLocation() != null) {
            user.setLocation(userDTO.getLocation());
        } else {
            user.setLocation(user.getLocation());
        }
        if (userDTO.getAbout() != null) {
            user.setAbout(userDTO.getAbout());
        } else {
            user.setAbout(user.getAbout());
        }
        if (userDTO.getGitHub() != null) {
            user.setGitHub(userDTO.getGitHub());
        } else {
            user.setGitHub(user.getGitHub());
        }
        if (userDTO.getLinkedin() != null) {
            user.setLinkedin(userDTO.getLinkedin());
        } else {
            user.setLinkedin(user.getLinkedin());
        }
        if (userDTO.getFacebook() != null) {
            user.setFacebook(userDTO.getFacebook());
        } else {
            user.setFacebook(user.getFacebook());
        }
        if (userDTO.getInstagram() != null) {
            user.setInstagram(userDTO.getInstagram());
        } else {
            user.setInstagram(user.getInstagram());
        }



        return userRepository.save(user);
    }

    @Override
    public User upgradeUser(String nickname) {
        User user = userRepository.getByNickname(nickname);

            List<Role> roles = new ArrayList<>();
            Role adminRole = new Role("ADMIN");
            roles.add(adminRole);

            user.setRoles(roles);

            return userRepository.save(user);
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
@Override
    public UserBasicDTO convertToUserBasicDTO(User user) {
        UserBasicDTO userBasicDTO = new UserBasicDTO();
        userBasicDTO.setFirstName(user.getFirstName());
        userBasicDTO.setLastName(user.getLastName());
        userBasicDTO.setEmail(user.getEmail());
        return userBasicDTO;
    }
}
