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
     * @param login the user login
     * @param certificateId the certificate id
     */
    void add(String login, long certificateId);

    /**
     * Find all user orders.
     *
     * @return the list of found orders
     */
    List<UserOrderDto> findAllUserOrders(String login, Integer pageNumber, Integer size);

    /**
     * Find most popular tag.
     *
     * @return the tag
     */
    TagDto findMostPopularHighCostTag();

    /**
     * Find order price date
     *
     * @param orderId the order id
     * @return the found order
     */
    OrderPriceDateDto findOrderPriceDate(long orderId);
}
