package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.PaginationException;
import com.epam.esm.exception.UserException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.UserService;
import com.epam.esm.validator.DataValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void add(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        DataValidator<User> validator = new DataValidator<>();
        Optional<List<String>> errorMessage = validator.isDataCorrect(user);

        if (errorMessage.isPresent()) {
            throw new IncorrectParameterValueException(errorMessage.get());
        }
        Optional<User> foundUser = userDao.findByLogin(user.getLogin());
        if (!foundUser.isPresent()) {
            user.setAdmin(false);
            userDao.add(user);
        } else {
            throw new UserException(ExceptionMessageKey.LOGIN_ALREADY_EXIST);
        }
    }

    @Override
    public UserDto findUserByLogin(String login) {
        if (Objects.isNull(login)) {
            throw new IncorrectParameterValueException();
        }
        Optional<User> foundUser = userDao.findByLogin(login);
        return foundUser
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessageKey.USER_NOT_FOUND_BY_ID));
    }

    @Override
    public List<UserDto> findAll(Integer pageNumber, Integer size) {
        pageNumber = Objects.isNull(pageNumber) ? DEFAULT_PAGE_NUMBER : pageNumber;
        size = Objects.isNull(size) ? DEFAULT_PAGE_SIZE : size;
        if (pageNumber <= 0 || size <= 0) {
            throw new PaginationException(ExceptionMessageKey.INCORRECT_PAGINATION_DATA);
        }
        List<User> users = userDao.findAll(pageNumber, size);
        return users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
