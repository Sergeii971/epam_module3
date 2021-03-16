package com.epam.esm.security;

import com.epam.esm.dto.ErrorDto;
import com.epam.esm.exception.JwtTokenMalformedException;
import com.epam.esm.exception.JwtTokenMissingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final Logger logger = LogManager.getLogger(ExceptionHandlerFilter.class);
    private static final int FORBIDDEN_CODE = 43;
    private static final String PROPERTIES_FILE_NAME = "properties.exception_messages";
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response);

        } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
            setErrorResponse(request, response, e);
        }
    }

    private void setErrorResponse(HttpServletRequest request, HttpServletResponse response, Throwable exception) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        Locale locale = request.getLocale();
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE_NAME, locale);
        String message = rb.getString(exception.getMessage());
        ErrorDto errorDto = new ErrorDto(message, FORBIDDEN_CODE);
        try {
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write( mapper.writeValueAsString(errorDto));
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
