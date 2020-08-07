package com.epam.cdp.module3.homework4.service.impl.criteria;

import com.epam.cdp.module3.homework4.dao.criteria.EntityCriteriaDao;
import com.epam.cdp.module3.homework4.domain.Address;
import com.epam.cdp.module3.homework4.domain.Employee;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.enumeration.EmployeeStatus;
import com.epam.cdp.module3.homework4.service.AbstractEntityCriteriaService;
import com.epam.cdp.module3.homework4.service.EmployeeService;

public class EmployeeCriteriaService extends AbstractEntityCriteriaService<Employee> implements EmployeeService {

    private EntityCriteriaDao<Employee> employeeCriteriaDao;

    public EmployeeCriteriaService(EntityCriteriaDao<Employee> employeeCriteriaDao) {
        this.employeeCriteriaDao = employeeCriteriaDao;
    }

    @Override
    public Long createEmployee(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addEmployeeToUnit(long employeeId, long unitId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void assignEmployeeForProject(long employeeId, long projectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityCriteriaDao<Employee> getCriteriaDao() {
        return employeeCriteriaDao;
    }
}
