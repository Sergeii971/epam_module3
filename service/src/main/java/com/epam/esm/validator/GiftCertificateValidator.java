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
public class GiftCertificateValidator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    /**
     * Validate gift certificate data.
     *
     * @param certificate the gift certificate
     * @return the error message
     */
    public Optional<List<String>> isGiftCertificateDataCorrect(GiftCertificate certificate) {
        Optional<List<String>> errorMessagesOptionalFormat = Optional.empty();
        List<String> errorMessages = new ArrayList<>();
        Set<ConstraintViolation<GiftCertificate>> violations = validator.validate(certificate);

        for (ConstraintViolation<GiftCertificate> violation : violations) {
            errorMessages.add(violation.getMessage());
        }
        if (errorMessages.size() != 0) {
            errorMessagesOptionalFormat = Optional.of(errorMessages);
        }
        return errorMessagesOptionalFormat;
    }
}
