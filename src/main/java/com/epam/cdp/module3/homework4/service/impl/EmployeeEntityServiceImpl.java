package com.epam.cdp.module3.homework4.service.impl;

import com.epam.cdp.module3.homework4.dao.EntityDao;
import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import com.epam.cdp.module3.homework4.domain.Address;
import com.epam.cdp.module3.homework4.domain.Developer;
import com.epam.cdp.module3.homework4.domain.Employee;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.EmployeeQa;
import com.epam.cdp.module3.homework4.domain.Project;
import com.epam.cdp.module3.homework4.domain.Unit;
import com.epam.cdp.module3.homework4.domain.enumeration.EmployeeStatus;
import com.epam.cdp.module3.homework4.service.EmployeeEntityService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeEntityServiceImpl extends EmployeeServiceImpl implements EmployeeEntityService {

    private EntityDao<EmployeeQa> employeeQaDao;
    private EntityDao<Developer> developerDao;

    /**
     * Constructor for EmployeeEntityService
     * @param employeeDao - dao for Employee entity
     * @param employeeQaDao - dao for EmployeeQa entity
     * @param developerDao - dao for Developer entity
     * @param unitDao - dao for Unit entity
     * @param projectDao - dao for Project entity
     */
    public EmployeeEntityServiceImpl(EntityDao<Employee> employeeDao, EntityDao<EmployeeQa> employeeQaDao,
                                     EntityDao<Developer> developerDao, EntityDao<Unit> unitDao,
                                     EntityDao<Project> projectDao) {
        super(employeeDao, unitDao, projectDao);
        this.employeeQaDao = employeeQaDao;
        this.developerDao = developerDao;
    }

    @Override
    public Long createEmployeeQa(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status) {
        EmployeeQa employeeQa = new EmployeeQa();
        employeeQa.setPersonalInfo(personalInfo);
        employeeQa.setAddress(address);
        employeeQa.setStatus(status);

        Transaction transaction = null;
        Long employeeQaId;
        try {
            transaction = getCurrentSession().beginTransaction();
            employeeQaId = employeeQaDao.save(employeeQa);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return employeeQaId;
    }

    @Override
    public Long createDeveloperEmployee(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status) {
        Developer developer = new Developer();
        developer.setPersonalInfo(personalInfo);
        developer.setAddress(address);
        developer.setStatus(status);

        Transaction transaction = null;
        Long developerId;
        try {
            transaction = getCurrentSession().beginTransaction();
            developerId = developerDao.save(developer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return developerId;
    }

    @Override
    public List<EmployeeQa> findAllQas() {
        Transaction transaction = null;
        List<EmployeeQa> employeeQaList = null;
        try {
            transaction = getCurrentSession().beginTransaction();
            employeeQaList = employeeQaDao.findAllEntities();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return employeeQaList;
    }

    @Override
    public List<Developer> findAllDevs() {
        Transaction transaction = null;
        List<Developer> employeeQaList = null;
        try {
            transaction = getCurrentSession().beginTransaction();
            employeeQaList = developerDao.findAllEntities();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return employeeQaList;
    }

    private Session getCurrentSession() {
        return EntitySessionFactory.getSessionFactory().getCurrentSession();
    }
}
