package com.epam.esm.handler;

import com.epam.esm.dto.ErrorDto;
import com.epam.esm.exception.BalanceException;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.InvalidUserCredentialsException;
import com.epam.esm.exception.JwtTokenMalformedException;
import com.epam.esm.exception.JwtTokenMissingException;
import com.epam.esm.exception.OrderException;
import com.epam.esm.exception.PaginationException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.exception.TagException;
import com.epam.esm.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.Locale;

/**
 * The type ExceptionProcessor.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@RestControllerAdvice
public class ExceptionProcessor extends ResponseEntityExceptionHandler {
    private static final int INCORRECT_PARAMETER_VALUE_CODE = 40;
    private static final int RESOURCE_NOT_FOUND_CODE = 44;
    private static final int PAYMENT_REQUIRED = 42;
    private static final int UNAUTHORIZED_CODE = 41;
    private static final int FORBIDDEN_CODE = 43;
    private static final String EXCEPTION_400 = "exception_400";
    private final ExceptionMessageCreator exceptionMessageCreator;

    @Autowired
    public ExceptionProcessor(ExceptionMessageCreator exceptionMessageCreator) {
        this.exceptionMessageCreator = exceptionMessageCreator;
    }


    @ExceptionHandler({AuthenticationException.class, MissingCsrfTokenException.class, InvalidUserCredentialsException.class,
            InvalidCsrfTokenException.class, SessionAuthenticationException.class})
    public ErrorDto handleAuthenticationException(RuntimeException ex, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return new ErrorDto(UrlUtils.buildFullRequestUrl(request), UNAUTHORIZED_CODE);
    }

    /**
     * handle Incorrect Parameter Value Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = IncorrectParameterValueException.class)
    public ResponseEntity<ErrorDto> handleIncorrectParameterValueException(IncorrectParameterValueException exception,
                                                                           Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getErrorMessages(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    /**
     * handle user Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ErrorDto> handleUserException(UserException exception,
                                                                     Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    /**
     * handle order Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = OrderException.class)
    public ResponseEntity<ErrorDto> handleOrderException(OrderException exception,
                                                                     Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    /**
     * handle balance Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = BalanceException.class)
    public ResponseEntity<ErrorDto> handleBalanceException(BalanceException exception,
                                                         Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, PAYMENT_REQUIRED), HttpStatus.PAYMENT_REQUIRED);
    }

    /**
     * handle tag Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = TagException.class)
    public ResponseEntity<ErrorDto> handleTagException(TagException exception,
                                                         Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    /**
     * handle pagination Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = PaginationException.class)
    public ResponseEntity<ErrorDto> handlePaginationException(PaginationException exception,
                                                              Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    /**
     * handle Resource Not Found Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException exception,  Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, RESOURCE_NOT_FOUND_CODE), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status,
                                                                   WebRequest request) {
        return new ResponseEntity<>(new ErrorDto(ex.getLocalizedMessage(),RESOURCE_NOT_FOUND_CODE),
                new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * handle validation Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ErrorDto> handleRException(ValidationException exception,  Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(EXCEPTION_400, locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }
}
