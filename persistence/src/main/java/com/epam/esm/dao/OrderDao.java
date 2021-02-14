package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.UserOrder;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    UserOrder add(UserOrder order);

    List<UserOrder> findAllUserOrders(String login, int pageNumber, int size);

    Tag findMostPopularHighCostTag();

    Optional<UserOrder> findById(long orderId);
}
