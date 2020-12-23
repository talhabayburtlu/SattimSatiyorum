package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.dao.UserRepository;
import com.springboot.SattimSatiyorum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(int userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null)
            throw new RuntimeException("User with id: " + userId + " can't found.");

        return user;
    }

    @Override
    public void save(User user) {
        user.getaPackage().getUsers().add(user);
        user.setCreatedAt(new Date((new java.util.Date().getTime())));
        user.setUpdatedAt(new Date((new java.util.Date().getTime())));
        userRepository.save(user);
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }
}
