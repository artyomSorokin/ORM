package com.epam.cdp.module3.homework4.dao.hql;

import com.epam.cdp.module3.homework4.domain.Project;

public class ProjectHqlDao extends EntityHqlDao<Project> {

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }
}
