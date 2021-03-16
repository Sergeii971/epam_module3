package com.epam.esm.service;

import com.epam.esm.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface UserService.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface UserService extends UserDetailsService {
    /**
     * Add user
     *
     * @param userDto the user dto
     */
    UserDto add(UserDto userDto);

    /**
     * Find user by login.
     *
     * @return the found user
     */
    UserDto findUserById(long userId);

    /**
     * Find all users.
     *
     * @return the list of found users
     */
    List<UserDto> findAll(Integer pageNumber, Integer size);
}
