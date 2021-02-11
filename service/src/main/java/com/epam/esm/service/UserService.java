package com.epam.esm.service;

import com.epam.esm.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    void add(UserDto userDto);

    UserDto findUserByLogin(String login);

    List<UserDto> findAll();
}
