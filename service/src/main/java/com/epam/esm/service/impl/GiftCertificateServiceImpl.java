package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.DataValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final TagService tagService;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String EMPTY_VALUE = "";

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagService tagService) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public void add(GiftCertificateDto giftCertificateDto) {
        addAndSetTags(giftCertificateDto);
        GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        DataValidator<GiftCertificate> validator = new DataValidator<>();
        Optional<List<String>> errorMessage = validator.isDataCorrect(giftCertificate);

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
    public GiftCertificateDto updateGiftCertificate(GiftCertificateDto modifiedGiftCertificateDto) {
        GiftCertificate modifiedGiftCertificate = modelMapper.map(modifiedGiftCertificateDto, GiftCertificate.class);
        Optional<GiftCertificate> foundGiftCertificate = giftCertificateDao.findById(modifiedGiftCertificateDto
                .getCertificateId());
        if (!foundGiftCertificate.isPresent()) {
            throw new ResourceNotFoundException(ExceptionMessageKey.GIFT_CERTIFICATE_NOT_FOUND_BY_ID);
        }
        updateGiftCertificateFields(foundGiftCertificate.get(), modifiedGiftCertificate);
        DataValidator<GiftCertificate> validator = new DataValidator<>();
        Optional<List<String>> errorMessage = validator.isDataCorrect(modifiedGiftCertificate);

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
            GiftCertificateQueryParametersDto giftCertificateQueryParametersDto) {
        GiftCertificateQueryParameters giftCertificateQueryParameters
                = modelMapper.map(giftCertificateQueryParametersDto, GiftCertificateQueryParameters.class);
        List<GiftCertificate> foundGiftCertificates;
        if (isQueryFindByTagName(giftCertificateQueryParameters)) {
            prepareParametersForRequest(giftCertificateQueryParameters);
            foundGiftCertificates = giftCertificateDao.findByTagName(giftCertificateQueryParameters);
        } else {
            prepareParametersForRequest(giftCertificateQueryParameters);
            foundGiftCertificates = giftCertificateDao.findByQueryParameters(giftCertificateQueryParameters);
        }
        return foundGiftCertificates
                .stream()
                .map(this::convertGiftCertificateAndSetTags)
                .collect(Collectors.toList());
    }

    private GiftCertificateDto convertGiftCertificateAndSetTags(GiftCertificate giftCertificate) {
        GiftCertificateDto giftCertificateDto = modelMapper.map(giftCertificate, GiftCertificateDto.class);
        giftCertificateDto.setTags(tagService.findTagsByGiftCertificateId(giftCertificateDto.getCertificateId()));
        return giftCertificateDto;
    }


    private void addAndSetTags(GiftCertificateDto giftCertificateDto) {
        List<TagDto> tags = new ArrayList<>();
        if (Objects.nonNull(giftCertificateDto.getTags())) {
            for (TagDto tagDto : giftCertificateDto.getTags()) {
                TagDto add = tagService.add(tagDto);
                tags.add(add);
            }
        }
        giftCertificateDto.setTags(tags);
    }

    private boolean isQueryFindByTagName(GiftCertificateQueryParameters parameters) {
        return (Objects.nonNull(parameters.getTagName()) && Objects.isNull(parameters.getName())
                && Objects.isNull(parameters.getDescription()));
    }

    private void prepareParametersForRequest(GiftCertificateQueryParameters parameters) {
        if (Objects.isNull(parameters.getTagName())) {
            parameters.setTagName(EMPTY_VALUE);
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
        foundGiftCertificate.setLastUpdateDate(LocalDateTime.now());
    }
}
