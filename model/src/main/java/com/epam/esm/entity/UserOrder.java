package com.epam.esm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "`order`")
public class UserOrder implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private long orderId;
    @Column(name = "date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_login", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "gift_certificate_certificateId", nullable=false)
    private GiftCertificate giftCertificate;

    public UserOrder() {

    }

    public UserOrder(LocalDateTime date, User user, GiftCertificate giftCertificate) {
        this.date = date;
        this.user = user;
        this.giftCertificate = giftCertificate;
    }

    public UserOrder(long orderId, LocalDateTime date, User user, GiftCertificate giftCertificate) {
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
        UserOrder order = (UserOrder) o;

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
