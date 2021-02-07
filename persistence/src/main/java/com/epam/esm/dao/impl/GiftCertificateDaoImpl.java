package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * The type GiftCertificateDaoImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    @PersistenceContext
    EntityManager entityManager;

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
        return Optional.of(entityManager.find(GiftCertificate.class, certificateId));
    }

    @Override
    public List<GiftCertificate> findByQueryParameters(GiftCertificateQueryParameters parameters) {
        String query1 = new StringBuilder()
                .append(DatabaseQuery.FIND_BY_QUERY_PARAMETERS)
                .append(parameters.getSortType().getSortType())
                .append(" ")
                .append(parameters.getOrderType().getOrderType())
                .toString();
        Query query = entityManager.createNativeQuery(query1, GiftCertificate.class);
        return query.setParameter(1, parameters.getName())
                .setParameter(2, parameters.getDescription())
                .getResultList();
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        return entityManager.merge(giftCertificate);
    }

    @Override
    public List<GiftCertificate> findByTagName(GiftCertificateQueryParameters parameters) {
        String query1 = new StringBuilder()
                .append(DatabaseQuery.FIND_BY_TAG_NAME)
                .append(" ")
                .append(parameters.getSortType().getSortType())
                .append(" ")
                .append(parameters.getOrderType().getOrderType())
                .toString();
        Query query = entityManager.createNativeQuery(query1, GiftCertificate.class);
        return query.setParameter(1, parameters.getTagName())
                .getResultList();
    }
}
