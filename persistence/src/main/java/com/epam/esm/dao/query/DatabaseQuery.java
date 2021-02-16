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
    public static final String FIND_CERTIFICATE_BY_TAG_NAME = "SELECT certificateId, gift_certificate.name, "
            + "description, price, duration, createDate, lastUpdateDate, isBought FROM gift_certificate "
            + "INNER JOIN gift_certificate_has_tag ON gift_certificate.certificateId = gift_certificate_has_tag.gift_certificate_certificateId "
            + "INNER JOIN tag ON gift_certificate_has_tag.tag_tagId = tagId WHERE tag.name LIKE concat(?, '%')";
    public static final String FIND_BY_TAG_NAMES = "SELECT certificateId, gift_certificate.name, "
            + "description, price, duration, createDate, lastUpdateDate, isBought " +
            " FROM gift_certificate INNER JOIN gift_certificate_has_tag ON gift_certificate.certificateId" +
            " = gift_certificate_certificateId INNER JOIN tag ON gift_certificate_has_tag.tag_tagId = tagId " +
            " WHERE ";


    public static final String FIND_ALL_TAGS = "SELECT t FROM Tag t";
    public static final String FIND_MOST_POPULAR_AND_HIGH_COST = "SELECT tag.tagId, tag.name FROM gift_certificate " +
            "INNER JOIN gift_certificate_has_tag ON gift_certificate.certificateId = gift_certificate_has_tag.gift_certificate_certificateId " +
            "INNER JOIN tag ON gift_certificate_has_tag.tag_tagId = tagId WHERE isBought = true " +
            "GROUP BY tag.name HAVING COUNT(tag.name) >= (SELECT COUNT(tag.name) FROM gift_certificate INNER JOIN " +
            "gift_certificate_has_tag ON gift_certificate.certificateId = gift_certificate_has_tag.gift_certificate_certificateId " +
            "INNER JOIN tag ON gift_certificate_has_tag.tag_tagId = tagId GROUP BY tag.name ORDER BY COUNT(*) DESC LIMIT 1) " +
            "AND SUM(gift_certificate.price) >= (SELECT SUM(gift_certificate.price) FROM gift_certificate INNER JOIN " +
            "gift_certificate_has_tag ON gift_certificate.certificateId = gift_certificate_has_tag.gift_certificate_certificateId " +
            "INNER JOIN tag ON gift_certificate_has_tag.tag_tagId = tagId GROUP BY tag.name ORDER BY SUM(gift_certificate.price) DESC LIMIT 1) LIMIT 1";
    public static final String FIND_TAG_BY_NAME = "SELECT t FROM Tag t WHERE t.name = :name";

    public static final String FIND_ALL_USERS = "SELECT t FROM User t";

    public static final String FIND_ALL_USER_ORDERS = "SELECT t FROM UserOrder t WHERE t.user.login = :login";

    private DatabaseQuery() {
    }
}
