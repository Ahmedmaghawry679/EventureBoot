package com.example.SE_Project.service;

import com.example.SE_Project.model.User;
import com.example.SE_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null); // if using Optional
    }


    public boolean validateLogin(String email, String password) {
        User user = findByEmail(email);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}