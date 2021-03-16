package com.epam.esm.dao.impl;

import com.epam.esm.dao.ColumnName;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    private final EntityManager entityManager;

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
    public void remove(Tag tag) {
        entityManager.remove(tag);
    }

    @Override
    public List<Tag> findAll(int pageNumber, int size) {
        Query query = entityManager.createQuery(DatabaseQuery.FIND_ALL_TAGS, Tag.class);
        query.setFirstResult((pageNumber - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Optional<Tag> findById(long tagId) {
        Tag tag = entityManager.find(Tag.class, tagId);
        return Objects.isNull(tag) ? Optional.empty() : Optional.of(tag);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        Query query = entityManager.createQuery(DatabaseQuery.FIND_TAG_BY_NAME, Tag.class);
        List<Tag> tags = (List<Tag>) query.setParameter(ColumnName.TAG_NAME, name).getResultList();
        return Objects.isNull(tags) || tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }
}
