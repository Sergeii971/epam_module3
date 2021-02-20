package com.epam.esm.entity;

import java.util.List;

/**
 * The type GiftCertificateQueryParameters.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class GiftCertificateQueryParameters {
    private List<String> tagNames;
    private String name;
    private String description;
    private SortType sortType;
    private OrderType orderType;

    /**
     * The type SortType.
     *
     * @author Verbovskiy Sergei
     * @version 1.0
     */
    public enum SortType {
        NAME("ORDER BY gift_certificate.name"), CREATE_DATE("ORDER BY createDate"), DEFAULT("");
        private final String sortType;

        SortType(String sortType) {
            this.sortType = sortType;
        }

        public String getSortType() {
            return sortType;
        }
    }

    /**
     * The type OrderType.
     *
     * @author Verbovskiy Sergei
     * @version 1.0
     */
    public enum OrderType {
        ASC(" ASC"), DESC(" DESC"), DEFAULT("");

        private final String orderType;

        OrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrderType() {
            return orderType;
        }
    }

    public GiftCertificateQueryParameters() {
    }

    public GiftCertificateQueryParameters(List<String> tagNames, String name, String description, SortType sortType,
                                          OrderType orderType) {
        this.tagNames = tagNames;
        this.name = name;
        this.description = description;
        this.sortType = sortType;
        this.orderType = orderType;
    }

    /**
     * Gets tag name.
     *
     * @return the tag name
     */
    public List<String> getTagNames() {
        return tagNames;
    }

    /**
     * Sets tag name.
     *
     * @param tagNames the tag name
     */
    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets sort type.
     *
     * @return the sortType
     */
    public GiftCertificateQueryParameters.SortType getSortType() {
        return sortType;
    }

    /**
     * Sets sort type.
     *
     * @param sortType the sort type
     */
    public void setSortType(GiftCertificateQueryParameters.SortType sortType) {
        this.sortType = sortType;
    }

    /**
     * Gets order type.
     *
     * @return the orderType
     */
    public GiftCertificateQueryParameters.OrderType getOrderType() {
        return orderType;
    }

    /**
     * Sets order type.
     *
     * @param orderType the order type
     */
    public void setOrderType(GiftCertificateQueryParameters.OrderType orderType) {
        this.orderType = orderType;
    }
}
