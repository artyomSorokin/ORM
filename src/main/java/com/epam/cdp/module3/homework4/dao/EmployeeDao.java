package com.epam.cdp.module3.homework4.dao;

import com.epam.cdp.module3.homework4.domain.Employee;

public class EmployeeDao extends EntityDao<Employee> {

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
