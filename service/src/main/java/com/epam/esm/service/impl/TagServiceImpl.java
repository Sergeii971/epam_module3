package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type TagServiceImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public TagDto add(TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);
        TagValidator validator = new TagValidator();
        Optional<String> errorMessage = validator.isTagDataCorrect(tag);

        if (errorMessage.isPresent()) {
            throw new IncorrectParameterValueException(errorMessage.get());
        }
        Optional<Tag> existingTag = tagDao.findByName(tag.getName());
        Tag addedTag = existingTag.orElseGet(() -> tagDao.add(tag));
        return modelMapper.map(addedTag, TagDto.class);
    }

    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = tagDao.findAll();
        return tags
                .stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findTagById(long tagId) {
        Optional<Tag> foundTag = tagDao.findById(tagId);
        return foundTag.map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessageKey.TAG_NOT_FOUND_BY_ID));
    }

    @Override
    public List<TagDto> findTagsByGiftCertificateId(long giftCertificateId) {
        List<Tag> foundTags = tagDao.findByGiftCertificateId(giftCertificateId);
        return foundTags
                .stream()
                .map(foundTag -> modelMapper.map(foundTag, TagDto.class))
                .collect(Collectors.toList());
    }
}
