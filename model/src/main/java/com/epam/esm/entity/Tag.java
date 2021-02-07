package com.epam.esm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * The type Tag.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Entity
@Table(name = "tag")
public class Tag implements BaseEntity {
    @Id
    @Column(name = "tagId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;
    @Column(name = "name")
    @Size(min = 1, max = 45, message = "javax.validation.constraints.NotNull.message.tagName")
    private String name;
    @ManyToMany(mappedBy = "tags")
    private List<GiftCertificate> giftCertificates;

    public Tag() {
    }

    public Tag(long tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public Tag(long tagId, String name, List<GiftCertificate> giftCertificates) {
        this.tagId = tagId;
        this.name = name;
        this.giftCertificates = giftCertificates;
    }

    /**
     * Gets tag id.
     *
     * @return the tag id
     */
    public long getTagId() {
        return tagId;
    }

    /**
     * Sets tag id.
     *
     * @param tagId the tag id
     */
    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    /**
     * Gets tag name.
     *
     * @return the tag name
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

    public List<GiftCertificate> getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(List<GiftCertificate> giftCertificates) {
        this.giftCertificates = giftCertificates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o ==null || this.getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;

        return (tagId == tag.getTagId() && Objects.equals(name, tag.name)
                && Objects.equals(giftCertificates, tag.giftCertificates));
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += result * 31 + Long.hashCode(tagId);
        result += result * 31 + (name == null ? 0 : name.hashCode());
        result += result * 31 + (giftCertificates == null ? 0 : giftCertificates.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(tagId)
                .append(" ")
                .append(name)
                .append(" ")
                .append(giftCertificates)
                .toString();
    }
}
