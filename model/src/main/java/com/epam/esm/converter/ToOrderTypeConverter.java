package com.epam.esm.converter;

import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.exception.IncorrectParameterValueException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

/**
 * The type ToOrderTypeConverter.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Component
@RequestScope
public class ToOrderTypeConverter {
    /**
     * convert To Order Type.
     *
     * @return the OrderType
     */
    public GiftCertificateQueryParametersDto.OrderType convertToOrderType(String orderTypeStringFormat) {
        GiftCertificateQueryParametersDto.OrderType orderType = null;
        if (Objects.nonNull(orderTypeStringFormat)) {
            try {
                orderType = GiftCertificateQueryParametersDto.OrderType.valueOf(orderTypeStringFormat.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IncorrectParameterValueException("incorrect order type: " + orderTypeStringFormat);
            }
        }
        return orderType;
    }
}
