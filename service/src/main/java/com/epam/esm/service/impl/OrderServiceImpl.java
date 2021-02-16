package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.OrderPriceDateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserOrderDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.UserOrder;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.OrderException;
import com.epam.esm.exception.PaginationException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final GiftCertificateDao giftCertificateDao;
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserDao userDao;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, GiftCertificateDao giftCertificateDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.giftCertificateDao = giftCertificateDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void add(String login, long certificateId) {
        Optional<User> foundUser = userDao.findByLogin(login);

        if (!foundUser.isPresent()) {
            throw new ResourceNotFoundException(ExceptionMessageKey.USER_NOT_FOUND_BY_ID);
        }
        Optional<GiftCertificate> foundGiftCertificate = giftCertificateDao.findById(certificateId);
        if (!foundGiftCertificate.isPresent()) {
            throw new ResourceNotFoundException(ExceptionMessageKey.GIFT_CERTIFICATE_NOT_FOUND_BY_ID);
        }
        if (foundGiftCertificate.get().getIsBought()) {
            throw new OrderException(ExceptionMessageKey.CERTIFICATE_ALREADY_BOUGHT);
        }
        LocalDateTime orderDate = LocalDateTime.now();
        foundGiftCertificate.get().setIsBought(true);
        UserOrder order = new UserOrder(orderDate, foundUser.get(), foundGiftCertificate.get());
        orderDao.add(order);
    }

    @Override
    public List<UserOrderDto> findAllUserOrders(String login, Integer pageNumber, Integer size) {
        pageNumber = Objects.isNull(pageNumber) ? DEFAULT_PAGE_NUMBER : pageNumber;
        size = Objects.isNull(size) ? DEFAULT_PAGE_SIZE : size;
        if (pageNumber <= 0 || size <= 0) {
            throw new PaginationException(ExceptionMessageKey.INCORRECT_PAGINATION_DATA);
        }
        List<UserOrder> userOrders = orderDao.findAllUserOrders(login, pageNumber, size);
        return userOrders
                .stream()
                .map(order -> modelMapper.map(order, UserOrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findMostPopularHighCostTag() {
        Tag tag = orderDao.findMostPopularHighCostTag();
        return modelMapper.map(tag, TagDto.class);
    }

    @Override
    public OrderPriceDateDto findOrderPriceDate(long orderId) {
        Optional<UserOrder> order = orderDao.findById(orderId);
        if (!order.isPresent()) {
            throw new ResourceNotFoundException(ExceptionMessageKey.ORDER_NOT_FOUND_BY_ID);
        }
        BigDecimal price = order.get().getGiftCertificate().getPrice();
        LocalDateTime date = order.get().getDate();
        return new OrderPriceDateDto(price, date);
    }
}
