package com.epam.esm.service.impl;

import com.epam.esm.configuration.ServiceConfiguration;
import com.epam.esm.dao.impl.OrderDaoImpl;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.dto.UserOrderDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.UserOrder;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ContextConfiguration(classes = { ServiceConfiguration.class, OrderServiceImpl.class, OrderDaoImpl.class})
class OrderServiceImplTest {
    @Autowired
    @InjectMocks
    private OrderServiceImpl orderService;

    @Autowired
    @Mock
    private OrderDaoImpl dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllUserOrdersPositiveTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        BigDecimal price = new BigDecimal("12.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        List<TagDto> tagsDto = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tagsDto);
        String login = "q";
        String userName = "q";
        String surname = "q";
        boolean isAdmin = false;
        User user = new User(login, userName, surname, new BigDecimal(1), isAdmin);
        UserDto userDto = new UserDto(login, userName, surname, new BigDecimal(1), isAdmin);
        UserOrder order = new UserOrder(1, createDate, user, giftCertificate);
        List<UserOrder> orders = new ArrayList<>();
        orders.add(order);
        Mockito.when(dao.findAllUserOrders(login, 1, 1)).thenReturn(orders);
        List<UserOrderDto> expected = new ArrayList<>();
        expected.add(new UserOrderDto(1, createDate, userDto, giftCertificateDto));
        assertEquals(orderService.findAllUserOrders(login, 1, 1), expected);
    }

    @Test
    public void findAllUserOrdersNegativeTest() {
        long certificateId = 1;
        String name = "qqq";
        String description = "qqq";
        BigDecimal price = new BigDecimal("12.0");
        int duration = 12;
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = LocalDateTime.now();
        List<Tag> tags = new ArrayList<>();
        List<TagDto> tagsDto = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tags);
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(certificateId, name, description, price, duration, createDate,
                lastUpdateDate, true, tagsDto);
        String login = "q";
        String userName = "q";
        String surname = "q";
        boolean isAdmin = false;
        User user = new User(login, userName, surname, new BigDecimal(1), isAdmin);
        UserDto userDto = new UserDto(login, userName, surname, new BigDecimal(1), isAdmin);
        UserOrder order = new UserOrder(1, createDate, user, giftCertificate);
        List<UserOrder> orders = new ArrayList<>();
        orders.add(order);
        Mockito.when(dao.findAllUserOrders(login, 1, 1)).thenReturn(orders);
        List<UserOrderDto> expected = new ArrayList<>();
        expected.add(new UserOrderDto(2, createDate, userDto, giftCertificateDto));
        assertNotEquals(orderService.findAllUserOrders(login, 1, 1), expected);
    }

    @Test
    public void findMostPopularHighCostTagPositiveTest() {
        long tagId = 1;
        String name = "qqq";
        Tag tag = new Tag(tagId, name);
        TagDto expected = new TagDto(tagId, name);
        Mockito.when(dao.findMostPopularHighCostTag()).thenReturn(tag);
        assertEquals(orderService.findMostPopularHighCostTag(), expected);
    }

    @Test
    public void findMostPopularHighCostTagNegativeTest() {
        long tagId = 1;
        String name = "qqq";
        Tag tag = new Tag(tagId, name);
        TagDto expected = new TagDto(2, name);
        Mockito.when(dao.findMostPopularHighCostTag()).thenReturn(tag);
        assertNotEquals(orderService.findMostPopularHighCostTag(), expected);
    }
}