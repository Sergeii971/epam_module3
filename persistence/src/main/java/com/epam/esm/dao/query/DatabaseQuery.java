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
    public static final String FIND_MOST_POPULAR_AND_HIGH_COST = "SELECT tag.tagId, tag.name FROM (SELECT tag.tagId," +
            " tag.name, DENSE_RANK() OVER(PARTITION by tag.tagId) as rnk FROM `order` INNER JOIN `user` ON" +
            " order.user_userId = user.userId INNER JOIN `gift_certificate` ON order.gift_certificate_certificateId" +
            " = gift_certificate.certificateId  INNER JOIN gift_certificate_has_tag ON gift_certificate.certificateId" +
            " = gift_certificate_has_tag.gift_certificate_certificateId INNER JOIN tag ON gift_certificate_has_tag.tag_tagId" +
            " = tagId WHERE user.userId = ? AND isBought = true GROUP BY tag.tagId HAVING SUM(gift_certificate.price)" +
            " >= (SELECT SUM(gift_certificate.price) FROM `order` INNER JOIN `user` ON order.user_userId = user.userId" +
            " INNER JOIN `gift_certificate` ON order.gift_certificate_certificateId = gift_certificate.certificateId" +
            "  INNER JOIN gift_certificate_has_tag ON gift_certificate.certificateId = gift_certificate_has_tag.gift_certificate_certificateId" +
            " INNER JOIN tag ON gift_certificate_has_tag.tag_tagId = tagId WHERE user.userId = ? AND isBought = true" +
            "  GROUP BY tag.tagId ORDER BY SUM(gift_certificate.price) DESC LIMIT 1) ORDER BY COUNT(*) DESC) tag where rnk = 1";
    public static final String FIND_TAG_BY_NAME = "SELECT t FROM Tag t WHERE t.name = :name";

    public static final String FIND_ALL_USERS = "SELECT t FROM User t";
    public static final String FIND_USER_BY_LOGIN = "SELECT t FROM User t WHERE t.login = :login";

    public static final String FIND_ALL_USER_ORDERS = "SELECT t FROM UserOrder t WHERE t.user.userId = :userId";


    private DatabaseQuery() {
    }
}
