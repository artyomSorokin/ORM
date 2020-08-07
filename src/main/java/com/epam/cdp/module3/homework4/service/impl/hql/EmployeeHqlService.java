package com.epam.cdp.module3.homework4.service.impl.hql;

import com.epam.cdp.module3.homework4.dao.hql.EntityHqlDao;
import com.epam.cdp.module3.homework4.domain.Address;
import com.epam.cdp.module3.homework4.domain.Employee;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.enumeration.EmployeeStatus;
import com.epam.cdp.module3.homework4.service.AbstractEntityHqlService;
import com.epam.cdp.module3.homework4.service.EmployeeService;

public class EmployeeHqlService extends AbstractEntityHqlService<Employee> implements EmployeeService {

    private EntityHqlDao<Employee> employeeHqlDao;

    public EmployeeHqlService(EntityHqlDao<Employee> employeeHqlDao) {
        this.employeeHqlDao = employeeHqlDao;
    }

    @Override
    public Long createEmployee(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status) {
        Employee employee = new Employee();
        employee.setPersonalInfo(personalInfo);
        employee.setAddress(address);
        employee.setStatus(status);

        return createEntity(employee);
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
    protected EntityHqlDao<Employee> getEntityHqlDao() {
        return employeeHqlDao;
    }
}
