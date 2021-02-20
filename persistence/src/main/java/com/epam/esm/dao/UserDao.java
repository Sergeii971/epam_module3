package com.epam.esm.dao;

import com.epam.esm.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface UserDao.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface UserDao {
    /**
     * Add user
     *
     * @param user the user
     */
    User add(User user);

    /**
     * Find user by login.
     *
     * @return the found user
     */
    Optional<User> findByLogin(String login);

    /**
     * Find all users.
     *
     * @return the list of found users
     */
    List<User> findAll(int pageNumber, int size);

    /**
     * update user
     *
     * @param user the user
     */
    User update(User user);
}
