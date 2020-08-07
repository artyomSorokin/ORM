package com.epam.cdp.module3.homework4.dao;

import com.epam.cdp.module3.homework4.domain.Developer;

public class DeveloperDao extends EntityDao<Developer> {

    @Override
    public Class<Developer> getEntityClass() {
        return Developer.class;
    }
}
