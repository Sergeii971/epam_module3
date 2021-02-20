package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type GiftCertificateDaoImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    EntityManager entityManager;
    private static final String SEARCH_AMONG_ALL_TAG_NAMES_QUERY = "INSTR(group_concat(tag.name SEPARATOR ' '), ?) ";
    private static final String AND_SEPARATOR = " AND ";
    private static final String OR_SEPARATOR = " OR ";
    private static final String TAG_NAME_EQUALS = " tag.name = ? ";
    private static final String HAVING = " HAVING ";
    private static final String GROUP_BY_GIFT_CERTIFICATE_ID = " GROUP BY gift_certificate.certificateId ";

    @Autowired
    public GiftCertificateDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public GiftCertificate add(GiftCertificate giftCertificate) {
        entityManager.persist(giftCertificate);
        return giftCertificate;
    }

    @Override
    public void remove(GiftCertificate giftCertificate) {
        entityManager.remove(giftCertificate);
    }

    @Override
    public Optional<GiftCertificate> findById(long certificateId) {
        GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, certificateId);
        return Objects.isNull(giftCertificate) ? Optional.empty() : Optional.of(giftCertificate);
    }

    @Override
    public List<GiftCertificate> findByQueryParameters(GiftCertificateQueryParameters parameters, int pageNumber, int size) {
        String queryWithSort = new StringBuilder()
                .append(DatabaseQuery.FIND_BY_QUERY_PARAMETERS)
                .append(parameters.getSortType().getSortType())
                .append(" ")
                .append(parameters.getOrderType().getOrderType())
                .toString();
        Query query = entityManager.createNativeQuery(queryWithSort, GiftCertificate.class);
         query.setParameter(1, parameters.getName())
                .setParameter(2, parameters.getDescription());
        query.setFirstResult((pageNumber - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        return entityManager.merge(giftCertificate);
    }

    @Override
    public Optional<List<GiftCertificate>> findByTagName(String tagName) {
        Query query = entityManager.createNativeQuery(DatabaseQuery.FIND_CERTIFICATE_BY_TAG_NAME, GiftCertificate.class);
        return Optional.of(query.setParameter(1, tagName)
                .getResultList());
    }

    @Override
    public Optional<List<GiftCertificate>> findByTags(List<String> tagNames) {
        Query query = entityManager.createNativeQuery(createQueryForFindByTags(tagNames), GiftCertificate.class);
        for (int i = 0; i < tagNames.size() * 2; i++) {
            if (i >= tagNames.size()) {
                query.setParameter(i + 1, tagNames.get(i - tagNames.size()));
            } else {
                query.setParameter(i + 1, tagNames.get(i));
            }
        }
        return Optional.of(query.getResultList());
    }

    private String createQueryForFindByTags(List<String> tagNames) {
        StringBuilder builder = new StringBuilder();

        builder.append(DatabaseQuery.FIND_BY_TAG_NAMES);
        for (int i = 0; i < tagNames.size(); i++) {
            builder.append(TAG_NAME_EQUALS);
            if (tagNames.size() != 1 && i != tagNames.size() - 1) {
                builder.append(OR_SEPARATOR);
            }
        }
        builder.append(GROUP_BY_GIFT_CERTIFICATE_ID)
                .append(HAVING);
        for (int i = 0; i < tagNames.size(); i++) {
            builder.append(SEARCH_AMONG_ALL_TAG_NAMES_QUERY);
            if (tagNames.size() != 1 && i != tagNames.size() - 1) {
                builder.append(AND_SEPARATOR);
            }
        }
        return builder.toString();
    }
}
