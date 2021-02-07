package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * The type TagDto.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class TagDto implements BaseDto {
    @JsonProperty("tagId")
    private long tagId;
    @JsonProperty("name")
    private String name;

    public TagDto() {
    }

    public TagDto(long tagId, String name) {
        this.tagId = tagId;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o ==null || this.getClass() != o.getClass()) {
            return false;
        }
        TagDto tag = (TagDto) o;

        return (tagId == tag.getTagId() && Objects.equals(name, tag.name));
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += result * 31 + Long.hashCode(tagId);
        result += result * 31 + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(tagId).append(" ").append(name).toString();
    }
}
