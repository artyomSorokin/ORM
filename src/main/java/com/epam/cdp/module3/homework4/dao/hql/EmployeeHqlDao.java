package com.epam.cdp.module3.homework4.dao.hql;

import com.epam.cdp.module3.homework4.domain.Employee;

public class EmployeeHqlDao extends EntityHqlDao<Employee> {

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
