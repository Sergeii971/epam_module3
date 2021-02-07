package com.epam.esm.dto;

import com.epam.esm.converter.LocalDateTimeSerializer;
import com.epam.esm.converter.LocalDateTimeDeserializer;
import com.epam.esm.entity.GiftCertificate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type GiftCertificateDto.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class GiftCertificateDto implements BaseDto {
    @JsonProperty("certificateId")
    private long certificateId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("createDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDate;
    @JsonProperty("lastUpdateDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdateDate;
    @JsonProperty("tags")
    private List<TagDto> tags;

    public GiftCertificateDto() {
        tags = new ArrayList<>();
    }

    public GiftCertificateDto(long certificateId, String name, String description, BigDecimal price, int duration,
                              LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.certificateId = certificateId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        tags = new ArrayList<>();
    }

    public GiftCertificateDto(Long certificateId, String name, String description, BigDecimal price, int duration,
                              LocalDateTime createDate, LocalDateTime lastUpdateDate, List<TagDto> tags) {
        this.certificateId = certificateId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = tags;
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
     * @return the create date
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

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<TagDto> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<TagDto> tags) {
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
        GiftCertificateDto certificate = (GiftCertificateDto) o;

        return (certificateId == certificate.getCertificateId() && Objects.equals(name, certificate.name)
                && Objects.equals(description, certificate.description) && Objects.equals(createDate, certificate.createDate)
                && Objects.equals(lastUpdateDate, certificate.lastUpdateDate) && duration == certificate.getDuration()
                && Objects.equals(tags, certificate.tags) && Objects.equals(price, certificate.price));
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
