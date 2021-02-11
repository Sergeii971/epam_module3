package com.epam.esm.dao.query;

/**
 * The type DatabaseQuery.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class DatabaseQuery {
    public static final String FIND_ALL_GIFT_CERTIFICATE = "SELECT certificateId, name, description," +
            " price, duration, createDate, lastUpdateDate, isBought FROM gift_certificate";
    public static final String FIND_BY_QUERY_PARAMETERS = FIND_ALL_GIFT_CERTIFICATE + " WHERE " +
            "gift_certificate.name LIKE concat(?, '%') AND description LIKE concat(?, '%') GROUP BY certificateId ";
    public static final String FIND_BY_TAG_NAME = "SELECT certificateId, gift_certificate.name, "
            + "description, price, duration, createDate, lastUpdateDate, isBought FROM gift_certificate "
            + "INNER JOIN gift_certificate_has_tag ON gift_certificate.certificateId = gift_certificate_giftId "
            + "INNER JOIN tag ON gift_certificate_has_tag.tag_tagId = tagId WHERE tag.name LIKE concat(?, '%')";

    public static final String ADD_TAG = "INSERT INTO tag(name) VALUES (?)";
    public static final String FIND_ALL_TAGS = "SELECT tagId, name FROM tag";
    public static final String FIND_BY_GIFT_CERTIFICATE_ID = FIND_ALL_TAGS +
            " INNER JOIN gift_certificate_has_tag ON tag.tagId = gift_certificate_has_tag.tag_tagId WHERE "
            + "gift_certificate_has_tag.gift_certificate_giftId = ?";
    public static final String FIND_BY_NAME = "SELECT tagId, name FROM tag WHERE name = ?";
    public static final String TAG_REMOVE_GIFT_CERTIFICATE_HAS_TAG = "DELETE FROM gift_certificate_has_tag "
            + "WHERE tag_tagId = ?";

    public static final String FIND_ALL_USERS = "SELECT login, name, surname, isAdmin FROM user";

    private DatabaseQuery() {
    }
}
