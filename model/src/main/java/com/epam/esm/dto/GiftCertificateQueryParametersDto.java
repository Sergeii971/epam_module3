package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type BaseDto.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class GiftCertificateQueryParametersDto implements BaseDto {
    @JsonProperty("tagName")
    private String tagName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("sortType")
    private SortType sortType;
    @JsonProperty("orderType")
    private OrderType orderType;

    /**
     * The type SortType.
     *
     * @author Verbovskiy Sergei
     * @version 1.0
     */
    public enum SortType {
        NAME,
        CREATE_DATE
    }

    /**
     * The type OrderType.
     *
     * @author Verbovskiy Sergei
     * @version 1.0
     */
    public enum OrderType {
        ASC,
        DESC
    }

    public GiftCertificateQueryParametersDto() {
    }

    public GiftCertificateQueryParametersDto(String tagName, String name, String description, SortType sortType,
                                             OrderType orderType) {
        this.tagName = tagName;
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
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets tag name.
     *
     * @param tagName the tag name
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
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
    public SortType getSortType() {
        return sortType;
    }

    /**
     * Sets sort type.
     *
     * @param sortType the sort type
     */
    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    /**
     * Gets order type.
     *
     * @return the orderType
     */
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * Sets order type.
     *
     * @param orderType the order type
     */
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
