package com.epam.esm.controller;

import com.epam.esm.dto.AuthenticationData;
import com.epam.esm.entity.Role;
import com.epam.esm.entity.User;
import com.epam.esm.exception.DisabledUserException;
import com.epam.esm.exception.InvalidUserCredentialsException;
import com.epam.esm.exception.UserException;
import com.epam.esm.security.JwtUtil;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/authorization")
public class AuthorizationController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userAuthService;

    @Autowired
    public AuthorizationController(JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                                   UserService userAuthService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userAuthService = userAuthService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CsrfToken signIn(@RequestBody AuthenticationData data) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword()));
        UserDetails userDetails = userAuthService.loadUserByUsername(data.getLogin());
        Set<Role> roles = userDetails.
                getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(Role::valueOf)
                .collect(Collectors.toSet());
        User user = new User();
        user.setLogin(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRoles(roles);
        return jwtUtil.generateToken(user);
    }
}
