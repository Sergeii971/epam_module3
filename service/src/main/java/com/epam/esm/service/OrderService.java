package com.epam.esm.service;

import com.epam.esm.dto.OrderPriceDateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserOrderDto;

import java.util.List;

public interface OrderService {
    void add(String login, long certificateId);

    List<UserOrderDto> findAllUserOrders(String login, int page, int size);

    TagDto findMostPopularHighCostTag();

    OrderPriceDateDto findOrderPriceDate(long orderId);
}
