package com.epam.cdp.module3.homework4.service.impl.criteria;

import com.epam.cdp.module3.homework4.dao.criteria.EntityCriteriaDao;
import com.epam.cdp.module3.homework4.domain.Project;
import com.epam.cdp.module3.homework4.service.AbstractEntityCriteriaService;
import com.epam.cdp.module3.homework4.service.ProjectService;

public class ProjectCriteriaService extends AbstractEntityCriteriaService<Project> implements ProjectService {

    private EntityCriteriaDao<Project> projectCriteriaDao;

    public ProjectCriteriaService(EntityCriteriaDao<Project> projectCriteriaDao) {
        this.projectCriteriaDao = projectCriteriaDao;
    }

    @Override
    public Long createProject(String name, String owner) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityCriteriaDao<Project> getCriteriaDao() {
        return projectCriteriaDao;
    }
}
