package com.epam.esm.dao;

import com.epam.esm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> findByLogin(String login);

    List<User> findAll();
}
