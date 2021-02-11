package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type TagDaoImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Repository
public class TagDaoImpl implements TagDao {
    EntityManager entityManager;

    @Autowired
    public TagDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tag add(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        Query query = entityManager.createNativeQuery(DatabaseQuery.FIND_ALL_TAGS, Tag.class);
        return query.getResultList();
    }

    @Override
    public Optional<Tag> findById(long tagId) {
        Tag tag = entityManager.find(Tag.class, tagId);
        return Objects.isNull(tag) ? Optional.empty() : Optional.of(tag);
    }


    @Override
    public List<Tag> findByGiftCertificateId(long certificateId) {
        Query query = entityManager.createNativeQuery(DatabaseQuery.FIND_BY_GIFT_CERTIFICATE_ID,
                Tag.class);
        return query.setParameter(1, certificateId)
                .getResultList();
    }
}
