package com.epam.esm.handler;

import com.epam.esm.dto.ErrorDto;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.OrderException;
import com.epam.esm.exception.PaginationException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.exception.TagException;
import com.epam.esm.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    private static final String EXCEPTION_400 = "exception_400";
    private final ExceptionMessageCreator exceptionMessageCreator;

    @Autowired
    public ExceptionProcessor(ExceptionMessageCreator exceptionMessageCreator) {
        this.exceptionMessageCreator = exceptionMessageCreator;
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

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ErrorDto> handleUserException(UserException exception,
                                                                     Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrderException.class)
    public ResponseEntity<ErrorDto> handleOrderException(OrderException exception,
                                                                     Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TagException.class)
    public ResponseEntity<ErrorDto> handleTagException(TagException exception,
                                                         Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ErrorDto> handleRException(ValidationException exception,  Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(EXCEPTION_400, locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }
}
