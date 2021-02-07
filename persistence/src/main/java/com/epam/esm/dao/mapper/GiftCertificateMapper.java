package com.epam.esm.dao.mapper;

import com.epam.esm.dao.ColumnName;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type GiftCertificateMapper.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class GiftCertificateMapper implements RowMapper<GiftCertificate> {
    /**
     * create gift certificate from result set
     *
     * @param resultSet the result set
     * @param rowNum the row number
     */
    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long certificateId = resultSet.getLong(ColumnName.GIFT_CERTIFICATE_ID);
        String name = resultSet.getString(ColumnName.GIFT_CERTIFICATE_NAME);
        String description = resultSet.getString(ColumnName.DESCRIPTION);
        BigDecimal price = resultSet.getBigDecimal(ColumnName.PRICE);
        int duration = resultSet.getInt(ColumnName.DURATION);
        LocalDateTime createDate = LocalDateTime.of(resultSet.getDate(ColumnName.CREATE_DATE).toLocalDate(),
                resultSet.getTime(ColumnName.CREATE_DATE).toLocalTime());
        LocalDateTime lastUpdateDate = LocalDateTime.of(resultSet.getDate(ColumnName.LAST_UPDATE_DATE).toLocalDate(),
                resultSet.getTime(ColumnName.LAST_UPDATE_DATE).toLocalTime());
        List<Tag> tags = new ArrayList<>();
        return new GiftCertificate(certificateId, name , description, price, duration, createDate,
                lastUpdateDate, true, tags);
    }
}
