package com.epam.cdp.module3.homework4.service;

import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import com.epam.cdp.module3.homework4.dao.hql.EntityHqlDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public abstract class AbstractEntityHqlService<T> implements EntityService<T> {

    @Override
    public Optional<T> findEntityById(long id) {
        Transaction transaction = null;
        Optional<T> entityById;
        try {
            transaction = getSession().beginTransaction();
            entityById = Optional.ofNullable(getEntityHqlDao().findEntityById(id));
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
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            getEntityHqlDao().deleteEntity(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Long createEntity(T entity) {
        throw new UnsupportedOperationException();
    }

    protected abstract EntityHqlDao<T> getEntityHqlDao();

    private Session getSession() {
        return EntitySessionFactory.getSessionFactory().getCurrentSession();
    }
}
