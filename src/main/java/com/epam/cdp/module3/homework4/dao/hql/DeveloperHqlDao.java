package com.epam.cdp.module3.homework4.dao.hql;

import com.epam.cdp.module3.homework4.domain.Developer;

public class DeveloperHqlDao extends EntityHqlDao<Developer> {

    @Override
    public Class<Developer> getEntityClass() {
        return Developer.class;
    }
}
