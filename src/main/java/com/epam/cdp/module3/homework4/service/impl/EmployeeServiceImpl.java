package com.epam.cdp.module3.homework4.service.impl;

import com.epam.cdp.module3.homework4.dao.EntityDao;
import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import com.epam.cdp.module3.homework4.domain.Address;
import com.epam.cdp.module3.homework4.domain.Employee;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.Project;
import com.epam.cdp.module3.homework4.domain.Unit;
import com.epam.cdp.module3.homework4.domain.enumeration.EmployeeStatus;
import com.epam.cdp.module3.homework4.service.AbstractEntityService;
import com.epam.cdp.module3.homework4.service.EmployeeService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeServiceImpl extends AbstractEntityService<Employee> implements EmployeeService {

    private EntityDao<Employee> employeeDao;
    private EntityDao<Unit> unitDao;
    private EntityDao<Project> projectDao;

    EmployeeServiceImpl(EntityDao<Employee> employeeDao, EntityDao<Unit> unitDao,
                               EntityDao<Project> projectDao) {
        this.employeeDao = employeeDao;
        this.unitDao = unitDao;
        this.projectDao = projectDao;
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
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            Employee employee = employeeDao.load(employeeId);
            Unit unit = unitDao.load(unitId);
            employee.setUnit(unit);
            employeeDao.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void assignEmployeeForProject(long employeeId, long projectId) {
        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            Employee employee = employeeDao.load(employeeId);
            Project project = projectDao.load(projectId);
            employee.getProjects().add(project);
            employeeDao.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void updateEntityById(Long employeeId, Employee entity) {
        entity.setEmployeeId(employeeId);
        updateEntity(entity);
    }

    @Override
    public EntityDao<Employee> getEntityDao() {
        return employeeDao;
    }

    private Session getSession() {
        return EntitySessionFactory.getSessionFactory().getCurrentSession();
    }
}
