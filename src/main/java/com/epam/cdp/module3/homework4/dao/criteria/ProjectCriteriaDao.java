package com.epam.cdp.module3.homework4.dao.criteria;

import com.epam.cdp.module3.homework4.domain.Project;

public class ProjectCriteriaDao extends EntityCriteriaDao<Project> {

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }

    @Override
    public String getIdName() {
        return "projectId";
    }
}
