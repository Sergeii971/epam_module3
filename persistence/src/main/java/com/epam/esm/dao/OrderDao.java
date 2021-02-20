package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.UserOrder;

import java.util.List;
import java.util.Optional;

/**
 * The interface OrderDao.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface OrderDao {
    /**
     * Add order
     *
     * @param order the order
     */
    UserOrder add(UserOrder order);

    /**
     * Find all user orders.
     *
     * @return the list of found orders
     */
    List<UserOrder> findAllUserOrders(String login, int pageNumber, int size);

    /**
     * Find most popular tag.
     *
     * @return the tag
     */
    Tag findMostPopularHighCostTag();

    /**
     * Find order by id.
     *
     * @return the found order
     */
    Optional<UserOrder> findById(long orderId);
}
