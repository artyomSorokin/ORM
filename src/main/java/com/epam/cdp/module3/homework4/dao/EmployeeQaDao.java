package com.epam.cdp.module3.homework4.dao;

import com.epam.cdp.module3.homework4.domain.EmployeeQa;

public class EmployeeQaDao extends EntityDao<EmployeeQa> {

    @Override
    public Class<EmployeeQa> getEntityClass() {
        return EmployeeQa.class;
    }
}
