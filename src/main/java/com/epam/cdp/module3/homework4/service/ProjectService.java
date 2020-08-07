package com.epam.cdp.module3.homework4.service;

import com.epam.cdp.module3.homework4.domain.Project;

public interface ProjectService extends EntityService<Project> {

    Long createProject(String name, String owner);

}
