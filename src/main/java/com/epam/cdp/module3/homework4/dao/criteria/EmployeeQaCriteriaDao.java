package com.epam.cdp.module3.homework4.dao.criteria;

import com.epam.cdp.module3.homework4.domain.EmployeeQa;

public class EmployeeQaCriteriaDao extends EntityCriteriaDao<EmployeeQa> {

    @Override
    public Class<EmployeeQa> getEntityClass() {
        return EmployeeQa.class;
    }

    @Override
    public String getIdName() {
        return "employeeId";
    }
}
