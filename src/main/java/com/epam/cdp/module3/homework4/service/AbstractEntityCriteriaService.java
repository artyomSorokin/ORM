package com.epam.cdp.module3.homework4.service;

import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import com.epam.cdp.module3.homework4.dao.criteria.EntityCriteriaDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public abstract class AbstractEntityCriteriaService<T> implements EntityService<T> {

    @Override
    public Optional<T> findEntityById(long id) {
        Transaction transaction = null;
        Optional<T> entityById;
        try {
            transaction = getSession().beginTransaction();
            entityById = Optional.ofNullable(getCriteriaDao().findEntityById(id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return entityById;
    }

    @Override
    public void updateEntityById(Long id, T entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteEntityById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long createEntity(T entity) {
        throw new UnsupportedOperationException();
    }

    public abstract EntityCriteriaDao<T> getCriteriaDao();

    private Session getSession() {
        return EntitySessionFactory.getSessionFactory().getCurrentSession();
    }
}
