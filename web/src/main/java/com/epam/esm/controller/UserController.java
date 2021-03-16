package com.epam.esm.controller;

import com.epam.esm.dto.UserDto;
import com.epam.esm.hateoas.UserHateoas;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * The type UserController.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final ApplicationContext applicationContext;

    @Autowired
    public UserController(UserService userService, ApplicationContext applicationContext) {
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    /**
     * Add user
     *
     * @param userDto the user dto
     */
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    /**
     * Find all users.
     *
     * @return the list of found users
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findAllUsers(@QueryParam("pageNumber") Integer pageNumber,
                                                 @QueryParam("size") Integer size) {
        List<UserDto> users = userService.findAll(pageNumber, size);
        UserHateoas userHateoas = applicationContext.getBean(UserHateoas.class);
        return userHateoas.addLinksForFindAllUsers(users);
    }

    /**
     * find user by id
     *
     * @param id the user id
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto findUserById(@PathVariable(value = "userId") long id) {
        return userService.findUserById(id);
    }
}
