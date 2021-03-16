package com.epam.esm.dto;

import com.epam.esm.converter.LocalDateTimeDeserializer;
import com.epam.esm.converter.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The type OrderPriceDateDto.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class OrderPriceDateDto {
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    public OrderPriceDateDto() {
    }

    public OrderPriceDateDto(BigDecimal price, LocalDateTime date) {
        this.price = price;
        this.date = date;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
