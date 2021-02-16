package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.OrderException;
import com.epam.esm.exception.PaginationException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.exception.TagException;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.DataValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private final GiftCertificateDao giftCertificateDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao, GiftCertificateDao giftCertificateDao) {
        this.tagDao = tagDao;
        this.giftCertificateDao = giftCertificateDao;
    }

    @Override
    @Transactional
    public TagDto add(TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);
        DataValidator<Tag> validator = new DataValidator<>();
        Optional<List<String>> errorMessage = validator.isDataCorrect(tag);

        if (errorMessage.isPresent()) {
            throw new IncorrectParameterValueException(errorMessage.get());
        }
        Optional<Tag> existingTag = tagDao.findByName(tag.getName());

        if (existingTag.isPresent()) {
            throw new TagException(ExceptionMessageKey.TAG_NAME_ALREADY_EXIST);
        }
        Tag addedTag = tagDao.add(tag);

        return modelMapper.map(addedTag, TagDto.class);
    }

    @Override
    @Transactional
    public void remove(long tagId) {
        Optional<Tag> tag = tagDao.findById(tagId);

        if (!tag.isPresent()) {
            throw new ResourceNotFoundException(ExceptionMessageKey.TAG_NOT_FOUND_BY_ID);
        }
        Optional<List<GiftCertificate>> giftCertificates = giftCertificateDao.findByTagName(tag.get().getName());

        if (giftCertificates.isPresent() && !giftCertificates.get().isEmpty()) {
            throw new OrderException(ExceptionMessageKey.Tag_USE_BY_PURCHASED_CERTIFICATE);
        }
        tagDao.remove(tag.get());
    }

    @Override
    public List<TagDto> findAll( Integer pageNumber, Integer size) {
        pageNumber = Objects.isNull(pageNumber) ? DEFAULT_PAGE_NUMBER : pageNumber;
        size = Objects.isNull(size) ? DEFAULT_PAGE_SIZE : size;
        if (pageNumber <= 0 || size <= 0) {
            throw new PaginationException(ExceptionMessageKey.INCORRECT_PAGINATION_DATA);
        }
        List<Tag> tags = tagDao.findAll(pageNumber, size);
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
}
