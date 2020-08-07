package com.epam.cdp.module3.homework4.service.impl.criteria;

import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import com.epam.cdp.module3.homework4.dao.criteria.EntityCriteriaDao;
import com.epam.cdp.module3.homework4.domain.Address;
import com.epam.cdp.module3.homework4.domain.Developer;
import com.epam.cdp.module3.homework4.domain.Employee;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.EmployeeQa;
import com.epam.cdp.module3.homework4.domain.enumeration.EmployeeStatus;
import com.epam.cdp.module3.homework4.service.EmployeeEntityService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeEntityCriteriaService extends EmployeeCriteriaService implements EmployeeEntityService {

    private EntityCriteriaDao<EmployeeQa> employeeQaDao;
    private EntityCriteriaDao<Developer> developerDao;

    /**
     * Constructor for EmployeeEntityCriteriaService.
     * @param employeeHqlDao - dao for Employee entity.
     * @param employeeQaDao - dao for EmployeeQa entity.
     * @param developerDao - dao for Developer entity.
     */
    public EmployeeEntityCriteriaService(EntityCriteriaDao<Employee> employeeHqlDao,
                                         EntityCriteriaDao<EmployeeQa> employeeQaDao,
                                         EntityCriteriaDao<Developer> developerDao
    ) {
        super(employeeHqlDao);
        this.employeeQaDao = employeeQaDao;
        this.developerDao = developerDao;
    }

    @Override
    public Long createEmployeeQa(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long createDeveloperEmployee(EmployeePersonalInfo personalInfo, Address address, EmployeeStatus status) {
        throw new UnsupportedOperationException();
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
