package com.epam.esm.service.impl;

import com.epam.esm.configuration.ServiceConfiguration;
import com.epam.esm.dao.impl.UserDaoImpl;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ContextConfiguration(classes = { ServiceConfiguration.class, UserServiceImpl.class, UserDaoImpl.class})
class UserServiceImplTest {

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    @Autowired
    @Mock
    private UserDaoImpl dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserByLoginPositiveTest() {
        String login = "q";
        String name = "q";
        String surname = "q";
        boolean isAdmin = false;
        User user = new User(1, login, name, surname, new BigDecimal(1), isAdmin);
        UserDto expected = new UserDto(login, name, surname, new BigDecimal(1), isAdmin);
        Mockito.when(dao.findById(1)).thenReturn(Optional.of(user));
        assertEquals(userService.findUserById(1), expected);
    }

    @Test
    public void findUserByLoginNegativeTest() {
        String login = "q";
        String name = "q";
        String surname = "q";
        boolean isAdmin = false;
        User user = new User(1, login, name, surname, new BigDecimal(1), isAdmin);
        UserDto expected = new UserDto(login, name, surname, new BigDecimal(1), true);
        Mockito.when(dao.findById(1)).thenReturn(Optional.of(user));
        assertNotEquals(userService.findUserById(1), expected);
    }

    @Test
    public void findAllPositiveTest() {
        String login = "q";
        String name = "q";
        String surname = "q";
        boolean isAdmin = false;
        User user = new User(1, login, name, surname, new BigDecimal(1), isAdmin);
        List<User> users  = new ArrayList<>();
        users.add(user);
        List<UserDto> expected = new ArrayList<>();
        expected.add(new UserDto(login, name, surname, new BigDecimal(1), false));
        Mockito.when(dao.findAll(1, 3)).thenReturn(users);
        assertEquals(userService.findAll(1, 3), expected);
    }

    @Test
    public void findAllNegativeTest() {
        String login = "q";
        String name = "q";
        String surname = "q";
        boolean isAdmin = false;
        User user = new User(1, login, name, surname, new BigDecimal(1), isAdmin);
        List<User> users  = new ArrayList<>();
        users.add(user);
        List<UserDto> expected = new ArrayList<>();
        expected.add(new UserDto(login, name, surname, new BigDecimal(1), true));
        Mockito.when(dao.findAll(1, 3)).thenReturn(users);
        assertNotEquals(userService.findAll(1, 3), expected);
    }
}