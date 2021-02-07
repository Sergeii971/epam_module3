package com.epam.esm.dao.mapper;

import com.epam.esm.dao.ColumnName;
import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type TagMapper.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class TagMapper implements RowMapper<Tag> {
    /**
     * create tag from result set
     *
     * @param resultSet the result set
     * @param rowNum the row number
     */
    @Override
    public Tag mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long tagId = resultSet.getLong(ColumnName.TAG_ID);
        String name = resultSet.getString(ColumnName.TAG_NAME);

        return new Tag(tagId, name);
    }
}
