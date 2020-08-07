package com.epam.cdp.module3.homework4.service;

import com.epam.cdp.module3.homework4.dao.EntityDao;
import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public abstract class AbstractEntityService<T> implements EntityService<T> {

    @Override
    public Optional<T> findEntityById(long id) {
        Transaction transaction = null;
        Optional<T> entityById;
        try {
            transaction = getSession().beginTransaction();
            entityById = Optional.ofNullable(getEntityDao().findEntityById(id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return entityById;
    }


    protected void updateEntity(T entity) {
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            getEntityDao().update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteEntityById(long id) {
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            T loadEntity = getEntityDao().load(id);
            getEntityDao().delete(loadEntity);
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
        Transaction transaction = null;
        Long entityId;
        try {
            transaction = getSession().beginTransaction();
            entityId = getEntityDao().save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return entityId;
    }

    public abstract void updateEntityById(Long entityId, T entity);

    public abstract EntityDao<T> getEntityDao();

    private Session getSession() {
        return EntitySessionFactory.getSessionFactory().getCurrentSession();
    }
}
