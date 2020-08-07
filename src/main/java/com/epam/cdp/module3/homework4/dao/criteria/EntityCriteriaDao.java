package com.epam.cdp.module3.homework4.dao.criteria;

import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class EntityCriteriaDao<T> {

    /**
     * Find entity dy id.
     * @param id - id of entity.
     * @return entity from database.
     */
    public T findEntityById(long id) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(getIdName()), id));
        Query<T> query = getCurrentSession().createQuery(criteriaQuery);
        query.setCacheable(true);
        return query.uniqueResult();
    }

    /**
     * Find all entities from database.
     * @return list of entity.
     */
    public List<T> findAllEntities() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(root);

        Query<T> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public abstract Class<T> getEntityClass();

    public abstract String getIdName();

    private Session getCurrentSession() {
        return EntitySessionFactory.getSessionFactory().getCurrentSession();
    }

}
