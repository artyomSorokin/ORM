package com.epam.cdp.module3.homework4.dao.criteria;

import com.epam.cdp.module3.homework4.domain.Employee;

public class EmployeeCriteriaDao extends EntityCriteriaDao<Employee> {

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public String getIdName() {
        return "employeeId";
    }
}
