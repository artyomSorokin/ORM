package com.epam.cdp.module3.homework4.service;

import com.epam.cdp.module3.homework4.domain.Address;
import com.epam.cdp.module3.homework4.domain.Employee;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.enumeration.EmployeeStatus;

public interface EmployeeService extends EntityService<Employee> {

    Long createEmployee(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status);

    void addEmployeeToUnit(long employeeId, long unitId);

    void assignEmployeeForProject(long employeeId, long projectId);

}
