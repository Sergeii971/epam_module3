package com.epam.esm.validator;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * The type TagValidator.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class TagValidator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    /**
     * Validate name.
     *
     * @param tag the tag
     * @return the error message
     */
    public Optional<String> isTagDataCorrect(Tag tag) {
        Optional<String> message = Optional.empty();
        StringBuilder builder = new StringBuilder();
        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);

        for (ConstraintViolation<Tag> violation : violations) {
            builder
                    .append(violation.getMessage());
        }
        if (builder.length() != 0) {
            message = Optional.of(builder.toString());
        }
        return message;

    }
}
