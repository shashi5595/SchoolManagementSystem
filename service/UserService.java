package com.bms.schoolmanagementsystem.service;

import com.bms.schoolmanagementsystem.entity.Role;
import com.bms.schoolmanagementsystem.entity.User;
import com.bms.schoolmanagementsystem.exception.UsernameAlreadyExistsException;
import com.bms.schoolmanagementsystem.repository.RoleRepository;
import com.bms.schoolmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(String username, String password, String roleName) throws Exception{
        if (userRepository.findByUsername(username) != null) {
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }

        // Create a new user entity
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // Fetch the role from the database based on the roleName
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RoleNotFoundException("Role not found.");
        }

        // Add the role to the user
        user.getRoles().add(role);

        // Save the user in the database
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return user;
    }
}

