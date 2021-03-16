package com.epam.esm.security;

import com.epam.esm.dto.UserDto;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.JwtTokenMissingException;
import com.epam.esm.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtCsrfFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private static final String JWT_TOKEN_HEADER_NAME = "x-csrf-token";

    public JwtCsrfFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(JWT_TOKEN_HEADER_NAME);
        if (Objects.isNull(token)) {
            throw new JwtTokenMissingException(ExceptionMessageKey.NO_JWT_TOKEN_IN_HEADER);
        }
        jwtUtil.validateToken(token);
        UserDto user = jwtUtil.findUserByToken(token);
        UserDetails userDetails = userService.loadUserByUsername(user.getLogin());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
