package com.epam.esm.validator;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GiftCertificateValidatorTest {
    @Test
    public void isGiftCertificateDataCorrectPositiveTest() {
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
        GiftCertificateValidator validator = new GiftCertificateValidator();
        Optional<List<String>> actual = validator.isGiftCertificateDataCorrect(giftCertificate);
        Optional<List<String>> expected = Optional.empty();
        assertEquals(actual, expected);
    }

    @Test
    public void isNameCorrectNegativeTest() {
        long certificateId = 1;
        String name = "";
        String description = "qqq";
        BigDecimal price = new BigDecimal("12.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        GiftCertificateValidator validator = new GiftCertificateValidator();
        Optional<List<String>> actual = validator.isGiftCertificateDataCorrect(giftCertificate);
        Optional<List<String>> expected = Optional.empty();
        assertNotEquals(actual, expected);
    }
}