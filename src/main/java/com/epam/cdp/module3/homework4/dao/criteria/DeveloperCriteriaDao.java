package com.epam.cdp.module3.homework4.dao.criteria;

import com.epam.cdp.module3.homework4.domain.Developer;

public class DeveloperCriteriaDao extends EntityCriteriaDao<Developer> {

    @Override
    public Class<Developer> getEntityClass() {
        return Developer.class;
    }

    @Override
    public String getIdName() {
        return "employeeId";
    }
}
