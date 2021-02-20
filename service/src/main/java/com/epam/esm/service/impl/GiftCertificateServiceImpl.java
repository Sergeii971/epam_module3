package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.OrderException;
import com.epam.esm.exception.PaginationException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.validator.DataValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type GiftCertificateServiceImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao giftCertificateDao;
    private final TagDao tagDao;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String EMPTY_VALUE = "";
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagDao tagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public void add(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        DataValidator<GiftCertificate> validator = new DataValidator<>();
        Optional<List<String>> errorMessage = validator.isDataCorrect(giftCertificate);

        addAndSetTags(giftCertificate);
        if (errorMessage.isPresent()) {
            throw new IncorrectParameterValueException(errorMessage.get());
        }
        LocalDateTime currentTime = LocalDateTime.now();
        giftCertificate.setCreateDate(currentTime);
        giftCertificate.setLastUpdateDate(currentTime);
        giftCertificateDao.add(giftCertificate);
    }

    @Override
    @Transactional
    public void remove(long certificateId) {
        Optional<GiftCertificate> giftCertificate = giftCertificateDao.findById(certificateId);

        if (!giftCertificate.isPresent()) {
            throw new ResourceNotFoundException(ExceptionMessageKey.GIFT_CERTIFICATE_NOT_FOUND_BY_ID);
        }
        if (giftCertificate.get().getIsBought()) {
            throw new OrderException(ExceptionMessageKey.CERTIFICATE_ALREADY_BOUGHT);
        }
        giftCertificateDao.remove(giftCertificate.get());
    }

    @Override
    @Transactional
    public GiftCertificateDto updateGiftCertificate(GiftCertificateDto modifiedGiftCertificateDto) {
        GiftCertificate modifiedGiftCertificate = modelMapper.map(modifiedGiftCertificateDto, GiftCertificate.class);
        Optional<GiftCertificate> foundGiftCertificate = giftCertificateDao.findById(modifiedGiftCertificateDto
                .getCertificateId());
        if (!foundGiftCertificate.isPresent()) {
            throw new ResourceNotFoundException(ExceptionMessageKey.GIFT_CERTIFICATE_NOT_FOUND_BY_ID);
        }
        if (foundGiftCertificate.get().getIsBought()) {
            throw new OrderException(ExceptionMessageKey.CERTIFICATE_ALREADY_BOUGHT);
        }
        updateGiftCertificateFields(foundGiftCertificate.get(), modifiedGiftCertificate);
        DataValidator<GiftCertificate> validator = new DataValidator<>();
        Optional<List<String>> errorMessage = validator.isDataCorrect(foundGiftCertificate.get());

        if (errorMessage.isPresent()) {
            throw new IncorrectParameterValueException(errorMessage.get());
        }
        GiftCertificate updatedGiftCertificate = giftCertificateDao.update(foundGiftCertificate.get());
        return modelMapper.map(updatedGiftCertificate, GiftCertificateDto.class);
    }

    @Override
    public GiftCertificateDto findGiftCertificateById(long certificateId) {
        Optional<GiftCertificate> foundGiftCertificate = giftCertificateDao.findById(certificateId);
        return foundGiftCertificate
                .map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessageKey.GIFT_CERTIFICATE_NOT_FOUND_BY_ID));
    }

    @Override
    public List<GiftCertificateDto> findGiftCertificatesByParameters(
            GiftCertificateQueryParametersDto giftCertificateQueryParametersDto, Integer pageNumber, Integer size) {
        pageNumber = Objects.isNull(pageNumber) ? DEFAULT_PAGE_NUMBER : pageNumber;
        size = Objects.isNull(size) ? DEFAULT_PAGE_SIZE : size;
        if (pageNumber <= 0 || size <= 0) {
            throw new PaginationException(ExceptionMessageKey.INCORRECT_PAGINATION_DATA);
        }
        GiftCertificateQueryParameters giftCertificateQueryParameters = modelMapper
                .map(giftCertificateQueryParametersDto, GiftCertificateQueryParameters.class);
        Optional<List<GiftCertificate>> foundGiftCertificates;
        if (isQueryFindByTagNames(giftCertificateQueryParameters)) {
            prepareParametersForRequest(giftCertificateQueryParameters);
            foundGiftCertificates = giftCertificateDao.findByTags(giftCertificateQueryParameters.getTagNames());
        } else {
            prepareParametersForRequest(giftCertificateQueryParameters);
            foundGiftCertificates = Optional.of(giftCertificateDao.findByQueryParameters(giftCertificateQueryParameters,
                    pageNumber,size));
        }
        return foundGiftCertificates
                .get()
                .stream()
                .map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .collect(Collectors.toList());
    }

    private void addAndSetTags(GiftCertificate giftCertificate) {
        List<Tag> tags = new ArrayList<>();

        if (Objects.nonNull(giftCertificate.getTags())) {
            Set<Tag> tagSet = new HashSet<>(giftCertificate.getTags());

            for (Tag tag : tagSet) {
                Optional<Tag> foundTag = tagDao.findByName(tag.getName());
                Tag addedTag = foundTag.orElseGet(() -> tagDao.add(tag));
                tags.add(addedTag);
            }
        }
        giftCertificate.setTags(tags);
    }

    private boolean isQueryFindByTagNames(GiftCertificateQueryParameters parameters) {
        return (Objects.nonNull(parameters.getTagNames()) || parameters.getTagNames().isEmpty())
                && Objects.isNull(parameters.getName())
                && Objects.isNull(parameters.getDescription());
    }

    private void prepareParametersForRequest(GiftCertificateQueryParameters parameters) {
        if (Objects.isNull(parameters.getTagNames())) {
            parameters.setTagNames(new ArrayList<>());
        }
        if (Objects.isNull(parameters.getName())) {
            parameters.setName(EMPTY_VALUE);
        }
        if (Objects.isNull(parameters.getDescription())) {
            parameters.setDescription(EMPTY_VALUE);
        }
        if (Objects.isNull(parameters.getSortType())) {
            parameters.setSortType(GiftCertificateQueryParameters.SortType.DEFAULT);
            parameters.setOrderType(GiftCertificateQueryParameters.OrderType.DEFAULT);
        } else {
            if (Objects.isNull(parameters.getOrderType())) {
                parameters.setOrderType(GiftCertificateQueryParameters.OrderType.DEFAULT);
            }
        }
    }

    private void updateGiftCertificateFields(GiftCertificate foundGiftCertificate, GiftCertificate modifiedGiftCertificate) {
        if (modifiedGiftCertificate.getDuration() != 0) {
            foundGiftCertificate.setDuration(modifiedGiftCertificate.getDuration());
        }
        if (Objects.nonNull(modifiedGiftCertificate.getName())) {
            foundGiftCertificate.setName(modifiedGiftCertificate.getName());
        }
        if (Objects.nonNull(modifiedGiftCertificate.getDescription())) {
            foundGiftCertificate.setDescription(modifiedGiftCertificate.getDescription());
        }
        if (Objects.nonNull(modifiedGiftCertificate.getPrice())) {
            foundGiftCertificate.setPrice(modifiedGiftCertificate.getPrice());
        }
        if (Objects.nonNull(modifiedGiftCertificate.getTags())) {
            addAndSetTags(modifiedGiftCertificate);
            foundGiftCertificate.setTags(modifiedGiftCertificate.getTags());
        }
        foundGiftCertificate.setLastUpdateDate(LocalDateTime.now());
    }
}
