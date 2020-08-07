package com.epam.cdp.module3.homework4.service.impl.hql;

import com.epam.cdp.module3.homework4.dao.EntityDao;
import com.epam.cdp.module3.homework4.domain.Project;
import com.epam.cdp.module3.homework4.service.AbstractEntityService;
import com.epam.cdp.module3.homework4.service.ProjectService;

public class ProjectHqlService extends AbstractEntityService<Project> implements ProjectService {

    private EntityDao<Project> projectHqlDao;

    public ProjectHqlService(EntityDao<Project> projectHqlDao) {
        this.projectHqlDao = projectHqlDao;
    }

    @Override
    public Long createProject(String name, String owner) {
        Project project = new Project();
        project.setProjectName(name);
        project.setOwner(owner);
        return createEntity(project);
    }

    @Override
    public void updateEntityById(Long projectId, Project entity) {
        entity.setProjectId(projectId);
        updateEntity(entity);
    }

    @Override
    public EntityDao<Project> getEntityDao() {
        return projectHqlDao;
    }
}
