package com.epam.esm.dto;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserOrderDto implements BaseDto {
    private long orderId;
    private LocalDateTime date;
    private final User user;
    private final GiftCertificate giftCertificate;

    public UserOrderDto() {
        user = new User();
        giftCertificate = new GiftCertificate();
    }

    public UserOrderDto(long orderId, LocalDateTime date, User user, GiftCertificate giftCertificate) {
        this.orderId = orderId;
        this.date = date;
        this.user = user;
        this.giftCertificate = giftCertificate;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
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
