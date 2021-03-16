package com.epam.esm.service;

import com.epam.esm.dto.OrderPriceDateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserOrderDto;

import java.util.List;

/**
 * The interface OrderService.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface OrderService {
    /**
     * Add order
     *
     * @param userId the user id
     * @param certificateId the certificate id
     */
    UserOrderDto add(long userId, long certificateId);

    /**
     * Find all user orders.
     *
     * @return the list of found orders
     */
    List<UserOrderDto> findAllUserOrders(long userId, Integer pageNumber, Integer size);

    /**
     * Find most popular tag.
     *
     * @return the tag
     */
    List<TagDto> findMostPopularHighCostTag(long userId);

    /**
     * Find order price date
     *
     * @param orderId the order id
     * @return the found order
     */
    OrderPriceDateDto findOrderPriceDate(long orderId);
}
