package com.example.demo.service.impl;

import com.example.demo.entity.*;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void save(User user, String roleName) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setAge(user.getAge());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRoleName(roleName);
        Set<Role>roles = new HashSet<>();
        roles.add(role);
        user1.setRoles(roles);
        userRepository.save(user1);
    }

    @Override
    public void update(Long id, User user, String roleName) {
     User user1 = userRepository.getById(id);
     user1.setUsername(user.getUsername());
     user1.setLastname(user.getLastname());
     user1.setEmail(user.getEmail());
     user1.setAge(user.getAge());
     user1.setPassword(passwordEncoder.encode(user.getPassword()));
     Role role = roleRepository.findByRoleName(roleName);
     Set<Role>roles = new HashSet<>();
     roles.add(role);
     user1.setRoles(roles);
     userRepository.save(user1);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByEmail(username);
    }


}
