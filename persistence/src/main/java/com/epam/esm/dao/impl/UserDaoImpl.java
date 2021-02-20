package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.query.DatabaseQuery;
import com.epam.esm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type UserDaoImpl.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {
    EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User add(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = entityManager.find(User.class, login);
        return Objects.isNull(user) ? Optional.empty() : Optional.of(user);
    }

    @Override
    public List<User> findAll(int pageNumber, int size) {
        Query query = entityManager.createQuery(DatabaseQuery.FIND_ALL_USERS, User.class);
        query.setFirstResult((pageNumber - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }
}
