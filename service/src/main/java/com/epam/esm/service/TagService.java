package com.epam.esm.service;

import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.IncorrectParameterValueException;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    void remove(long tagId);

    /**
     * Find all tags.
     *
     * @return the list of found tags
     */
    List<TagDto> findAll(Integer pageNumber, Integer size);

    /**
     * Find tag by id.
     *
     * @return TagDto
     */
    TagDto findTagById(long tagId);
}
