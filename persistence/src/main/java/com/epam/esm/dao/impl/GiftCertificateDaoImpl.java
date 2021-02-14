package com.epam.esm.dao.impl;

import com.epam.esm.dao.ColumnName;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import com.epam.esm.entity.UserOrder;
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
    public List<GiftCertificate> findByTagName(GiftCertificateQueryParameters parameters) {
        String queryWithSort = new StringBuilder()
                .append(DatabaseQuery.FIND_BY_TAG_NAME)
                .append(" ")
                .append(parameters.getSortType().getSortType())
                .append(" ")
                .append(parameters.getOrderType().getOrderType())
                .toString();
        Query query = entityManager.createNativeQuery(queryWithSort, GiftCertificate.class);
        return query.setParameter(1, parameters.getTagName())
                .getResultList();
    }

    @Override
    public Optional<List<GiftCertificate>> findByTags(List<String> tagNames) {
        StringBuilder builder = new StringBuilder();
        builder.append(DatabaseQuery.FIND_BY_TAG_NAMES);
        for (int i =0; i < tagNames.size(); i++) {
            builder.append(SEARCH_AMONG_ALL_TAG_NAMES_QUERY);
            if (tagNames.size() != 1 && i != tagNames.size() - 1) {
                builder.append(AND_SEPARATOR);
            }
        }
        Query query = entityManager.createNativeQuery(builder.toString(), GiftCertificate.class);
        for (int i = 0; i < tagNames.size(); i++) {
            query.setParameter(i + 1, tagNames.get(i));
        }
        Optional<List<GiftCertificate>> giftCertificates = Optional.of(query.getResultList());
        return giftCertificates;
    }


}
