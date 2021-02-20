package com.epam.esm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type UserOrder.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
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
    public User getUser() {
        return user;
    }

    /**
     * Gets gift certificate.
     *
     * @return the gift certificate
     */
    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets gift certificate.
     *
     * @param giftCertificate the gift certificate
     */
    public void setGiftCertificate(GiftCertificate giftCertificate) {
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
