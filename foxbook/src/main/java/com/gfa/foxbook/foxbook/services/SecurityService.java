package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.Role;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.dtos.security.RegisterDto;
import com.gfa.foxbook.foxbook.repositories.RoleRepository;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public void registerUser(RegisterDto registerDto) {
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);
    }

}
