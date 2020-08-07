package com.epam.cdp.module3.homework4.dao.hql;

import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public abstract class EntityHqlDao<T> {

    private static final String SELECT_FROM = "from %s";
    private static final String SELECT_FROM_WHERE_ID = "from %s where id = '%s'";
    private static final String DELETE_FROM_WHERE_ID = "delete from %s where id = '%s'";

    /**
     * Find entity dy id.
     * @param id - id of entity.
     * @return entity from database.
     */
    public T findEntityById(long id) {
        String currentQuery = String.format(SELECT_FROM_WHERE_ID, getEntityClass().getName(), id);
        Query<T> query = getCurrentSession().createQuery(currentQuery);
        query.setCacheable(true);
        return query.uniqueResult();
    }

    /**
     * Find all entities from database.
     * @return list of entity.
     */
    public List<T> findAllEntities() {
        String currentQuery = String.format(SELECT_FROM, getEntityClass().getName());
        Query<T> query = getCurrentSession().createQuery(currentQuery);
        return query.list();
    }

    /**
     * Delete entity from database.
     * @param id - id of entity.
     */
    public void deleteEntity(Long id) {
        String currentQuery = String.format(DELETE_FROM_WHERE_ID, getEntityClass().getName(), id);
        Query<T> query = getCurrentSession().createQuery(currentQuery);
        query.executeUpdate();
    }

    public abstract Class<T> getEntityClass();

    private Session getCurrentSession() {
        return EntitySessionFactory.getSessionFactory().getCurrentSession();
    }
}
