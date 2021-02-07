package com.epam.esm.converter;

import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.exception.IncorrectParameterValueException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

/**
 * The type ToSortTypeConverter.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Component
@RequestScope
public class ToSortTypeConverter {
    /**
     * convert To Sort Type.
     *
     * @return the SortType
     */
    public GiftCertificateQueryParametersDto.SortType convertToSortType(String sortTypeStringFormat) {
        GiftCertificateQueryParametersDto.SortType sortType = null;
        if (Objects.nonNull(sortTypeStringFormat)) {
            try {
                sortType = GiftCertificateQueryParametersDto.SortType.valueOf(sortTypeStringFormat.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IncorrectParameterValueException("incorrect sort type: " + sortTypeStringFormat);
            }
        }
        return sortType;
    }
}
