package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;

import java.util.List;
import java.util.Optional;

/**
 * The interface GiftCertificateDao.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface GiftCertificateDao {
    /**
     * Add gift certificate
     *
     * @param giftCertificate the gift certificate
     */
    GiftCertificate add(GiftCertificate giftCertificate);

    void remove(GiftCertificate giftCertificate);

    /**
     * Find gift certificate by id.
     *
     * @return the found gift certificate
     */
    Optional<GiftCertificate> findById(long certificateId);

    /**
     * Find all gift certificate by search parameters.
     *
     * @param giftCertificateQueryParameters the gift certificate query parameter
     * @return the list of found gift certificate
     */
    List<GiftCertificate> findByQueryParameters(GiftCertificateQueryParameters giftCertificateQueryParameters, int pageNumber, int size);

    /**
     * update gift certificate
     *
     * @param giftCertificate the gift certificate
     */
    GiftCertificate update(GiftCertificate giftCertificate);

    /**
     * Find gift certificate by tag name.
     *
     * @return the list of found gift certificate
     */
    Optional<List<GiftCertificate>> findByTagName(String tagName);

    /**
     * Find gift certificates by tags.
     *
     * @return the list of found gift certificate
     */
    Optional<List<GiftCertificate>> findByTags(List<String> tagNames);
}
