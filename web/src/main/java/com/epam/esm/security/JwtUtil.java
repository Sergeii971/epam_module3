package com.epam.esm.security;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.Role;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.JwtTokenMalformedException;
import com.epam.esm.exception.JwtTokenMissingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@PropertySource(value = "/jwt/JwtConfig.properties")
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.token.validity}")
    private long tokenValidity;
    private final Logger logger = LogManager.getLogger(JwtUtil.class);
    private static final String JWT_TOKEN_HEADER_NAME = "x-csrf-token";
    private static final String JWT_TOKEN_PARAMETER_NAME = "_csrf";
    private static final String USER_ROLES = "roles";
    private static final String STRING_EMPTY = "";

    public CsrfToken generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());
        claims.put(USER_ROLES, user.getRoles());
        String id = UUID.randomUUID().toString().replace("-", STRING_EMPTY);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);
        Date now = new Date(nowMillis);

        String token = STRING_EMPTY;
        try {
            token = Jwts.builder()
                    .setClaims(claims)
                    .setId(id)
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        } catch (JwtException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return new DefaultCsrfToken(JWT_TOKEN_HEADER_NAME, JWT_TOKEN_PARAMETER_NAME, token);
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException(ExceptionMessageKey.INVALID_JWT_SIGNATURE);
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException(ExceptionMessageKey.INCORRECT_JWT_TOKEN);
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException(ExceptionMessageKey.EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException(ExceptionMessageKey.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException(ExceptionMessageKey.JWT_CLAIMS_STRING_IS_EMPTY);
        }
    }

    public UserDto findUserByToken(final String token) {
        UserDto user = new UserDto();
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            user.setLogin(body.getSubject());
            Set<Role> roles = Arrays
                    .stream(body.get(USER_ROLES)
                            .toString()
                            .replaceAll("\\[", STRING_EMPTY)
                            .replaceAll("]", STRING_EMPTY)
                            .split(","))
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return user;
    }

    public String getSecret() {
        return secret;
    }
}
