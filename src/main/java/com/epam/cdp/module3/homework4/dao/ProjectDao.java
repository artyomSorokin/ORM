package com.epam.cdp.module3.homework4.dao;

import com.epam.cdp.module3.homework4.domain.Project;

public class ProjectDao extends EntityDao<Project> {

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }
}
