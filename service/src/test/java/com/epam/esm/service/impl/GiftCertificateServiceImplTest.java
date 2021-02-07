package com.epam.esm.service.impl;

import com.epam.esm.configuration.ServiceConfiguration;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = { ServiceConfiguration.class, GiftCertificateServiceImpl.class, TagServiceImpl.class,
        GiftCertificateDaoImpl.class})
class GiftCertificateServiceImplTest {

    @Autowired
    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    @Autowired
    @Mock
    private GiftCertificateDaoImpl dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findGiftCertificateByIdPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        BigDecimal price = new BigDecimal("12.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        Mockito.doReturn(Optional.of(giftCertificate)).when(dao).findById(1);
        GiftCertificateDto expected = new GiftCertificateDto(certificateId, name, description, price, duration,
                createDate, lastUpdateDate, new ArrayList<>());
        assertEquals(giftCertificateService.findGiftCertificateById(1), expected);
    }

    @Test
    public void findGiftCertificateByIdNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        BigDecimal price = new BigDecimal("12.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        Mockito.doReturn(Optional.of(giftCertificate)).when(dao).findById(1);
        GiftCertificateDto expected = new GiftCertificateDto(certificateId, name, description, price, duration,
                createDate, lastUpdateDate, null);
        assertNotEquals(giftCertificateService.findGiftCertificateById(1), expected);
    }

    @Test
    public void findGiftCertificateByIdExceptionTest() {
        Mockito.doReturn(Optional.empty()).when(dao).findById(1);
        assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.findGiftCertificateById(1));
    }
}