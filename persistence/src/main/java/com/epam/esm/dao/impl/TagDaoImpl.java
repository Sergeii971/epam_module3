package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper = new TagMapper();
    private static final int NAME_INDEX = 1;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag add(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(DatabaseQuery.ADD_TAG, Statement.RETURN_GENERATED_KEYS);
            statement.setString(NAME_INDEX, tag.getName());
            return statement;
        }, keyHolder);
        if (Objects.nonNull(keyHolder.getKey())) {
            tag.setTagId(keyHolder.getKey().longValue());
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(DatabaseQuery.FIND_ALL_TAGS, tagMapper);
    }

    @Override
    public Optional<Tag> findById(long tagId) {
        return jdbcTemplate.query(DatabaseQuery.FIND_TAG_BY_ID, new Object[]{tagId}, tagMapper).stream().findFirst();
    }

    @Override
    public void removeGiftCertificateHasTag(long id) {
        jdbcTemplate.update(DatabaseQuery.TAG_REMOVE_GIFT_CERTIFICATE_HAS_TAG, id);
    }


    @Override
    public List<Tag> findByGiftCertificateId(long certificateId) {
        return jdbcTemplate.query(DatabaseQuery.FIND_BY_GIFT_CERTIFICATE_ID, new Object[]{certificateId}, tagMapper);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(DatabaseQuery.FIND_BY_NAME, new Object[]{name}, tagMapper).stream().findAny();
    }
}
