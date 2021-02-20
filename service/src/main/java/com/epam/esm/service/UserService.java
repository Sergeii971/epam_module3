package com.epam.esm.service;

import com.epam.esm.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface UserService.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface UserService {
    /**
     * Add user
     *
     * @param userDto the user dto
     */
    void add(UserDto userDto);

    /**
     * Find user by login.
     *
     * @return the found user
     */
    UserDto findUserByLogin(String login);

    /**
     * Find all users.
     *
     * @return the list of found users
     */
    List<UserDto> findAll(Integer pageNumber, Integer size);
}
