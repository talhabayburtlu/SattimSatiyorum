package com.springboot.SattimSatiyorum.service;


import com.springboot.SattimSatiyorum.entity.User;

public interface UserService {

    User findById(int userId);

    void save(User user);

    void deleteById(int userId);

    User findUserWithUniques(String mail, String phoneNumber);
}
