package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.exception.IncorrectParameterValueException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface GiftCertificateService.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface GiftCertificateService {
    /**
     * Add gift certificate
     *
     * @param giftCertificateDto the gift certificate dto
     */
    GiftCertificateDto add(GiftCertificateDto giftCertificateDto);

    @Transactional
    void remove(long certificateId);

    /**
     * update gift certificate
     *
     * @param giftCertificateDto the gift certificate dto
     */
    GiftCertificateDto updateGiftCertificate(GiftCertificateDto giftCertificateDto);

    /**
     * Find gift certificate by id.
     *
     * @return GiftCertificateDto
     */
    GiftCertificateDto findGiftCertificateById(long certificateId);

    /**
     * Find all gift certificate by search parameters.
     *
     * @param giftCertificateQueryParametersDto the gift certificate query parameter dto
     * @return the list of found gift certificate
     */
    List<GiftCertificateDto> findGiftCertificatesByParameters(GiftCertificateQueryParametersDto giftCertificateQueryParametersDto,
                                                              Integer pageNumber, Integer size);
}
