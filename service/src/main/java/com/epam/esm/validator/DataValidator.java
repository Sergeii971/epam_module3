package com.epam.esm.validator;

import com.epam.esm.entity.GiftCertificate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The type GiftCertificateValidator.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class DataValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    /**
     * Validate gift certificate data.
     *
     * @param t the t
     * @return the error message
     */
    public Optional<List<String>> isDataCorrect(T t) {
        Optional<List<String>> errorMessagesOptionalFormat = Optional.empty();
        List<String> errorMessages = new ArrayList<>();
        Set<ConstraintViolation<T>> violations = validator.validate(t);

        for (ConstraintViolation<T> violation : violations) {
            errorMessages.add(violation.getMessage());
        }
        if (errorMessages.size() != 0) {
            errorMessagesOptionalFormat = Optional.of(errorMessages);
        }
        return errorMessagesOptionalFormat;

    }
}
