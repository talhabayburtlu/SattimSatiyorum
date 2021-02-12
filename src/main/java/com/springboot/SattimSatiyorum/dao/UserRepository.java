package com.springboot.SattimSatiyorum.dao;

import com.springboot.SattimSatiyorum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Query(value = "SELECT u.* " +
            "FROM User u " +
            "WHERE (:mail is null or u.mail = :mail) AND (:phoneNumber is null or u.phone_number = :phoneNumber) ",
            nativeQuery = true)
    User findUserWithUniques(@Param("mail") String mail, @Param("phoneNumber") String phoneNumber);

    @Transactional
    @Query(value = "SELECT u.* " +
            "FROM User u " +
            "WHERE u.mail = :mail", nativeQuery = true)
    Optional<User> findUserWithMail(@Param("mail") String mail);

}
