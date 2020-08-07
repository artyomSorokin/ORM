package com.epam.cdp.module3.homework4.dao;

import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import org.hibernate.Session;

import java.util.List;

public abstract class EntityDao<T> {

    public T load(long id) {
        return getCurrentSession().load(getEntityClass(), id);
    }

    public T findEntityById(long id) {
        return getCurrentSession().get(getEntityClass(), id);
    }

    public List<T> findAllEntities() {
        return getCurrentSession().createQuery("from " + getEntityClass().getName()).list();
    }

    public Long save(T entity) {
        return (Long) getCurrentSession().save(entity);
    }

    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public abstract Class<T> getEntityClass();

    private Session getCurrentSession() {
        return EntitySessionFactory.getSessionFactory().getCurrentSession();
    }
}
