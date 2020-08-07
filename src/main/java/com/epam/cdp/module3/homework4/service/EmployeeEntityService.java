package com.epam.cdp.module3.homework4.service;

import com.epam.cdp.module3.homework4.domain.Address;
import com.epam.cdp.module3.homework4.domain.Developer;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.EmployeeQa;
import com.epam.cdp.module3.homework4.domain.enumeration.EmployeeStatus;

import java.util.List;

public interface EmployeeEntityService extends EmployeeService {

    Long createEmployeeQa(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status);

    Long createDeveloperEmployee(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status);

    List<EmployeeQa> findAllQas();

    List<Developer> findAllDevs();
}
