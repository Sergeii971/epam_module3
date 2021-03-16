package com.epam.esm.dao.impl;

import com.epam.esm.dao.ColumnName;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type OrderDaoImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    private final EntityManager entityManager;

    @Autowired
    public OrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserOrder add(UserOrder order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public List<UserOrder> findAllUserOrders(long userId, int pageNumber, int size) {
        Query query = entityManager.createQuery(DatabaseQuery.FIND_ALL_USER_ORDERS, UserOrder.class);
        query.setFirstResult((pageNumber - 1) * size);
        query.setMaxResults(size);
        return query.setParameter(ColumnName.USER_ID, userId).getResultList();
    }

    @Override
    public List<Tag> findMostPopularHighCostTag(long userId) {
        Query query = entityManager.createNativeQuery(DatabaseQuery.FIND_MOST_POPULAR_AND_HIGH_COST, Tag.class);
        query.setParameter(1, userId);
        query.setParameter(2, userId);
        return query.getResultList();
    }

    @Override
    public Optional<UserOrder> findById(long orderId) {
        UserOrder order = entityManager.find(UserOrder.class, orderId);
        return Objects.isNull(order) ? Optional.empty() : Optional.of(order);
    }
}
