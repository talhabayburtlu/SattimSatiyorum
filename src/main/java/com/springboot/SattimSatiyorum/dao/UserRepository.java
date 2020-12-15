package com.springboot.SattimSatiyorum.dao;

import com.springboot.SattimSatiyorum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
