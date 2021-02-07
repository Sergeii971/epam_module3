package com.epam.esm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type GiftCertificate.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Entity
@Table(name = "gift_certificate")
public class GiftCertificate implements BaseEntity {
    @Id
    @Column(name = "certificateId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long certificateId;
    @Column(name = "name")
    @NotNull(message = "javax.validation.constraints.NotNull.message.name")
    @Size(min = 1, max = 45, message = "javax.validation.constraints.Size.message.name")
    private String name;
    @Column(name = "description")
    @NotNull(message = "javax.validation.constraints.NotNull.message.description")
    @Size(min = 1, max = 100, message = "javax.validation.constraints.Size.message.description")
    private String description;
    @Column(name = "price")
    @NotNull(message = "javax.validation.constraints.NotNull.message.price")
    @Min(value = 1, message = "javax.validation.constraints.Min.message.price")
    @Max(value = 100000, message = "javax.validation.constraints.Max.message.price")
    private BigDecimal price;
    @Column(name = "duration")
    @Min(value = 1, message = "javax.validation.constraints.Min.message.duration")
    @Max(value = 100, message = "javax.validation.constraints.Max.message.duration")
    private int duration;
    @Column(name = "createDate")
    private LocalDateTime createDate;
    @Column(name = "lastUpdateDate")
    private LocalDateTime lastUpdateDate;
    @Column(name = "isBought")
    private boolean isBought;
    @ManyToMany
    @JoinTable(name = "gift_certificate_has_tag", joinColumns = @JoinColumn(name = "gift_certificate_giftId"),
            inverseJoinColumns = @JoinColumn(name = "tag_tagId"))
    private List<Tag> tags;

    public GiftCertificate(long certificateId, String name, String description, BigDecimal price, int duration,
                           LocalDateTime createDate, LocalDateTime lastUpdateDate, boolean isBought) {
        this.certificateId = certificateId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.isBought = isBought;
        tags = new ArrayList<>();
    }

    public GiftCertificate(long certificateId, String name, String description, BigDecimal price, int duration,
                           LocalDateTime createDate, LocalDateTime lastUpdateDate,boolean isBought, List<Tag> tags) {
        this.certificateId = certificateId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.isBought = isBought;
        this.tags = tags;
    }

    public GiftCertificate() {
        tags = new ArrayList<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getCertificateId() {
        return certificateId;
    }

    /**
     * Sets id.
     *
     * @param certificateId the certificate id
     */
    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
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

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets create date.
     *
     * @return the createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets lastUpdateDate.
     *
     * @return the last update date
     */
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets last update date.
     *
     * @param lastUpdateDate the last update date
     */
    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o ==null || this.getClass() != o.getClass()) {
            return false;
        }
        GiftCertificate certificate = (GiftCertificate) o;

        return (certificateId == certificate.getCertificateId() && Objects.equals(name, certificate.name)
                && Objects.equals(description, certificate.description) && Objects.equals(createDate, certificate.createDate)
                && Objects.equals(lastUpdateDate, certificate.lastUpdateDate) && duration == certificate.getDuration()
                && Objects.equals(tags, certificate.tags) && Objects.equals(price, certificate.price)
                && isBought == certificate.isBought);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += result * 31 + Long.hashCode(certificateId);
        result += result * 31 + (name == null ? 0 : name.hashCode());
        result += result * 31 + (description == null ? 0 : description.hashCode());
        result += result * 31 + (createDate == null ? 0 : createDate.hashCode());
        result += result * 31 + (lastUpdateDate == null ? 0 : lastUpdateDate.hashCode());
        result += result * 31 + (tags == null ? 0 : tags.hashCode());
        result += result * 31 + duration;
        result += result * 31 + price.hashCode();
        result += result * 31 + Boolean.hashCode(isBought);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(certificateId)
                .append(" ").append(name)
                .append(" ").append(description)
                .append(" ")
                .append(price)
                .append(" ")
                .append(duration)
                .append(" ")
                .append(createDate)
                .append(" ")
                .append(lastUpdateDate)
                .append(" ")
                .append(tags)
                .toString();
    }
}
