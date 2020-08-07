package com.epam.cdp.module3.homework4.dao.hql;

import com.epam.cdp.module3.homework4.domain.EmployeeQa;

public class EmployeeQaHqlDao extends EntityHqlDao<EmployeeQa> {

    @Override
    public Class<EmployeeQa> getEntityClass() {
        return EmployeeQa.class;
    }
}
