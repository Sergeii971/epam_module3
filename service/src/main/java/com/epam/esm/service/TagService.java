package com.epam.esm.service;

import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.IncorrectParameterValueException;

import java.util.List;

/**
 * The interface TagService.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface TagService {
    /**
     * Add tag
     *
     * @param tagDto the tag dto
     */
    TagDto add(TagDto tagDto);

    /**
     * Find all tags.
     *
     * @return the list of found tags
     */
    List<TagDto> findAll();

    /**
     * Find tag by id.
     *
     * @return TagDto
     */
    TagDto findTagById(long tagId);

    /**
     * Find all tags by gift certificate id.
     *
     * @return the list of found tags
     */
    List<TagDto> findTagsByGiftCertificateId(long giftCertificateId);
}
