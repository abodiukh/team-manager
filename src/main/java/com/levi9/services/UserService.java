package com.levi9.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.levi9.domain.User;
import com.levi9.domain.UserRole;
import com.levi9.domain.Verification;
import com.levi9.dto.UserDTO;
import com.levi9.exceptions.EmailExistsException;
import com.levi9.repositories.UserRepository;
import com.levi9.repositories.UserRoleRepository;
import com.levi9.repositories.VerificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public User addUser(final UserDTO userDTO) throws EmailExistsException {
        if (emailExist(userDTO.getEmail())) {
            throw new EmailExistsException("There is an account with that email");
        }
        User user = new User(userDTO.getEmail(), userDTO.getName(), passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(false);
        UserRole userRole = new UserRole();
        userRole.setId(0L);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User updateUser(final UserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getId());
        Set<UserRole> roles = userDTO.getRoles().stream().map(role -> userRoleRepository.findByRole(role)).collect(Collectors.toSet());
        user.setRoles(roles);
        user.setEnabled(userDTO.isEnabled());
        return userRepository.save(user);
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }

    public void createVerificationToken(final User user, final String token) {
        verificationRepository.save(new Verification(token, user));
    }

    public void deleteVerificationToken(final Long userId) {
        Verification verification = verificationRepository.findByUserId(userId);
        if (verification != null) {
            verificationRepository.delete(verification);
        }
    }

    public Verification getVerificationToken(final String token) {
        return verificationRepository.findByToken(token);
    }

    public void deleteUser(final Long id) {
        userRepository.delete(id);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

}
