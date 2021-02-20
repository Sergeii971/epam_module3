package com.epam.esm.dto;

import com.epam.esm.converter.LocalDateTimeDeserializer;
import com.epam.esm.converter.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type UserOrderDto.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class UserOrderDto implements BaseDto {
    @JsonProperty("orderId")
    private long orderId;
    @JsonProperty("date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("giftCertificate")
    private GiftCertificateDto giftCertificate;

    public UserOrderDto() {
    }

    public UserOrderDto(long orderId, LocalDateTime date, UserDto user, GiftCertificateDto giftCertificate) {
        this.orderId = orderId;
        this.date = date;
        this.user = user;
        this.giftCertificate = giftCertificate;
    }

    /**
     * Gets id.
     *
     * @return the order id
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserDto getUser() {
        return user;
    }

    /**
     * Gets gift certificate.
     *
     * @return the gift certificate
     */
    public GiftCertificateDto getGiftCertificate() {
        return giftCertificate;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserDto user) {
        this.user = user;
    }

    /**
     * Sets gift certificate.
     *
     * @param giftCertificate the gift certificate
     */
    public void setGiftCertificate(GiftCertificateDto giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o ==null || this.getClass() != o.getClass()) {
            return false;
        }
        UserOrderDto order = (UserOrderDto) o;

        return (orderId == order.orderId && Objects.equals(date, order.date) && Objects.equals(user, order.user)
                && Objects.equals(giftCertificate, order.giftCertificate));

    }

    @Override
    public int hashCode() {
        int result = 1;

        result += result * 31 + Long.hashCode(orderId);
        result += result * 31 + (date == null ? 0 : date.hashCode());
        result += result * 31 + (user == null ? 0 : user.hashCode());
        result += result * 31 + (giftCertificate == null ? 0 : giftCertificate.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(orderId)
                .append(" ")
                .append(date)
                .append(" ")
                .append(user)
                .append(" ")
                .append(giftCertificate)
                .toString();
    }
}
