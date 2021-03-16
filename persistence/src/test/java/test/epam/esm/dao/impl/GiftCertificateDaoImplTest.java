package test.epam.esm.dao.impl;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateQueryParameters;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class GiftCertificateDaoImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private GiftCertificateDaoImpl giftCertificateDao;
    private static final String CREATE_TABLE_QUERY_FILENAME = "createDatabase.sql";

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
    .addScript(CREATE_TABLE_QUERY_FILENAME).setType(EmbeddedDatabaseType.H2)
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        //giftCertificateDao = new GiftCertificateDaoImpl(jdbcTemplate);
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void addPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        BigDecimal price = new BigDecimal("12.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        GiftCertificate giftCertificate1 = giftCertificateDao.add(giftCertificate);
        long addedGiftCertificateId = giftCertificate1.getCertificateId();
        Optional<GiftCertificate> actual = giftCertificateDao.findById(addedGiftCertificateId);
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(certificateId, name, description,
                price, duration, createDate, lastUpdateDate, true, tags));
        expected.get().setCertificateId(addedGiftCertificateId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        BigDecimal price = new BigDecimal("12.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        List<Tag> tags = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        GiftCertificate giftCertificate1 = giftCertificateDao.add(giftCertificate);
        long addedGiftCertificateId = giftCertificate1.getCertificateId();
        Optional<GiftCertificate> actual = giftCertificateDao.findById(addedGiftCertificateId);
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags));
        
        expected.get().setCertificateId(addedGiftCertificateId);
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void findByIdPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        BigDecimal price = new BigDecimal("12.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39,
                1);
        List<Tag> tags = new ArrayList<>();
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags));
        Optional<GiftCertificate> actual = giftCertificateDao.findById(certificateId);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByIdNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        BigDecimal price = new BigDecimal("4.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.of(2021, 1, 18, 13, 39, 1);
        LocalDateTime lastUpdateDate = LocalDateTime.of(2021, 1, 18, 13, 39,
                1);
        List<Tag> tags = new ArrayList<>();
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags));
        Optional<GiftCertificate> actual = giftCertificateDao.findById(certificateId);
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void updatePositiveTest() {
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
        GiftCertificate expected = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        GiftCertificate actual = giftCertificateDao.update(giftCertificate);
        expected.setCertificateId(actual.getCertificateId());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updateNegativeTest() {
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
        GiftCertificate expected = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        GiftCertificate actual = giftCertificateDao.update(giftCertificate);
        expected.setCertificateId(actual.getCertificateId());
        Assertions.assertEquals(expected, actual);
    }
}