package com.epam.cdp.module3.homework4.service;

import com.epam.cdp.module3.homework4.dao.DeveloperDao;
import com.epam.cdp.module3.homework4.dao.EmployeeDao;
import com.epam.cdp.module3.homework4.dao.EmployeeQaDao;
import com.epam.cdp.module3.homework4.util.EntitySessionFactory;
import com.epam.cdp.module3.homework4.dao.ProjectDao;
import com.epam.cdp.module3.homework4.dao.UnitDao;
import com.epam.cdp.module3.homework4.dao.criteria.DeveloperCriteriaDao;
import com.epam.cdp.module3.homework4.dao.criteria.EmployeeCriteriaDao;
import com.epam.cdp.module3.homework4.dao.criteria.EmployeeQaCriteriaDao;
import com.epam.cdp.module3.homework4.dao.hql.DeveloperHqlDao;
import com.epam.cdp.module3.homework4.dao.hql.EmployeeHqlDao;
import com.epam.cdp.module3.homework4.dao.hql.EmployeeQaHqlDao;
import com.epam.cdp.module3.homework4.domain.Address;
import com.epam.cdp.module3.homework4.domain.Developer;
import com.epam.cdp.module3.homework4.domain.Employee;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.EmployeeQa;
import com.epam.cdp.module3.homework4.domain.enumeration.EmployeeStatus;
import com.epam.cdp.module3.homework4.service.impl.EmployeeEntityServiceImpl;
import com.epam.cdp.module3.homework4.service.impl.ProjectServiceImpl;
import com.epam.cdp.module3.homework4.service.impl.UnitServiceImpl;
import com.epam.cdp.module3.homework4.service.impl.criteria.EmployeeEntityCriteriaService;
import com.epam.cdp.module3.homework4.service.impl.hql.EmployeeEntityHqlService;
import org.hibernate.stat.Statistics;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntityHibernateTest {

    private static final String FIRST_NAME = "Volodymir";
    private static final String LAST_NAME = "Bigus";
    private static final String UPDATE_FIRST_NAME = "Andrew";
    private static final String CITY = "Kharkov";
    private static final String STREET = "23_Avgusta";
    private static final String HOUSE = "33g";
    private static final String APARTMENT = "502";
    private static final String UNIT_NAME = "Java solution";
    private static final String PROJECT_NAME = "Nordstrom";
    private static final String OWNER = "Nordstrom Inc";
    private static final int AGE = 26;
    private static final String PHONE_NUMBER = "+3855525235";

    private static EmployeeEntityService employeeEntityService;
    private static EmployeeEntityHqlService employeeEntityHqlService;
    private static EmployeeEntityCriteriaService employeeEntityCriteriaService;
    private static UnitService unitService;
    private static ProjectService projectService;
    private static Statistics statistics;

    @BeforeClass
    public static void setUp() {
        UnitDao unitDao = new UnitDao();
        ProjectDao projectDao = new ProjectDao();
        employeeEntityService = new EmployeeEntityServiceImpl(
                new EmployeeDao(), new EmployeeQaDao(), new DeveloperDao(), unitDao, projectDao);
        employeeEntityHqlService = new EmployeeEntityHqlService(
                new EmployeeHqlDao(), new EmployeeQaHqlDao(), new DeveloperHqlDao());
        employeeEntityCriteriaService = new EmployeeEntityCriteriaService(
                new EmployeeCriteriaDao(), new EmployeeQaCriteriaDao(), new DeveloperCriteriaDao());

        unitService = new UnitServiceImpl(unitDao);
        projectService = new ProjectServiceImpl(projectDao);
        statistics = EntitySessionFactory.getSessionFactory().getStatistics();
    }

    @Test
    public void testCreateAndFindEmployeeById() {
        long createdEmployeeId = getCreatedEmployeeId();
        Optional<Employee> employee = employeeEntityService.findEntityById(createdEmployeeId);
        assertTrue(employee.isPresent());
        assertEquals(FIRST_NAME, employee.get().getPersonalInfo().getFirstName());
        assertEquals(CITY, employee.get().getAddress().getCity());
        assertEquals(EmployeeStatus.ACTIVE, employee.get().getStatus());
        employeeEntityService.deleteEntityById(createdEmployeeId);
    }

    @Test
    public void testCreateAndDeleteEmployeeById() {
        long createdEmployeeId = getCreatedEmployeeId();
        employeeEntityService.deleteEntityById(createdEmployeeId);
        Optional<Employee> employee = employeeEntityService.findEntityById(createdEmployeeId);
        assertFalse(employee.isPresent());
    }

    @Test
    public void testUpdateEmployeePersonalInfoById() {
        long createdEmployeeId = getCreatedEmployeeId();
        Employee employee = employeeEntityService.findEntityById(createdEmployeeId).get();
        EmployeePersonalInfo personalInfo = employee.getPersonalInfo();
        personalInfo.setFirstName(UPDATE_FIRST_NAME);
        employeeEntityService.updateEntityById(createdEmployeeId, employee);
        Employee updatedEmployee = employeeEntityService.findEntityById(createdEmployeeId).get();
        assertEquals(UPDATE_FIRST_NAME, updatedEmployee.getPersonalInfo().getFirstName());
        assertEquals(LAST_NAME, updatedEmployee.getPersonalInfo().getSurname());
        employeeEntityService.deleteEntityById(createdEmployeeId);
    }

    @Test
    public void testAddEmployeeToUnit() {
        long createdEmployeeId = getCreatedEmployeeId();
        long createdUnitId = unitService.createUnit(UNIT_NAME);

        employeeEntityService.addEmployeeToUnit(createdEmployeeId, createdUnitId);
        String actualUnitName = employeeEntityService.findEntityById(createdEmployeeId).get().getUnit().getUnitName();
        assertEquals(UNIT_NAME, actualUnitName);
        employeeEntityService.deleteEntityById(createdEmployeeId);
        unitService.deleteEntityById(createdUnitId);
    }

    @Test
    public void testAssignEmployeeToProject() {
        long createdEmployeeId = getCreatedEmployeeId();
        long createdProjectId = projectService.createProject(PROJECT_NAME, OWNER);

        employeeEntityService.assignEmployeeForProject(createdEmployeeId, createdProjectId);
        String actualProjectName = employeeEntityService.findEntityById(createdEmployeeId).get()
                .getProjects().iterator().next().getProjectName();
        assertEquals(PROJECT_NAME, actualProjectName);
        employeeEntityService.deleteEntityById(createdEmployeeId);
        projectService.deleteEntityById(createdProjectId);
    }

    @Test
    public void testFindEmployeeByIdWithCriteria() {
        long createdEmployeeId = getCreatedEmployeeId();
        Optional<Employee> employee = employeeEntityCriteriaService.findEntityById(createdEmployeeId);
        assertTrue(employee.isPresent());
        assertEquals(FIRST_NAME, employee.get().getPersonalInfo().getFirstName());
        assertEquals(CITY, employee.get().getAddress().getCity());
        assertEquals(EmployeeStatus.ACTIVE, employee.get().getStatus());
        employeeEntityService.deleteEntityById(createdEmployeeId);
    }

    @Test
    public void testFindEmployeeByIdWithHql() {
        long createdEmployeeId = getCreatedEmployeeId();
        Optional<Employee> employee = employeeEntityHqlService.findEntityById(createdEmployeeId);
        assertTrue(employee.isPresent());
        assertEquals(FIRST_NAME, employee.get().getPersonalInfo().getFirstName());
        assertEquals(CITY, employee.get().getAddress().getCity());
        assertEquals(EmployeeStatus.ACTIVE, employee.get().getStatus());
        employeeEntityHqlService.deleteEntityById(createdEmployeeId);
    }

    @Test
    public void testDeleteEmployeeByIdWithHql() {
        long createdEmployeeId = getCreatedEmployeeId();
        employeeEntityHqlService.deleteEntityById(createdEmployeeId);
        Optional<Employee> employee = employeeEntityHqlService.findEntityById(createdEmployeeId);
        assertFalse(employee.isPresent());
    }

    @Test
    public void testFindEmployeeQas() {
        long createdDevEmployeeId = getCreatedDevEmployeeId();
        long createdQaEmployeeId = getCreatedEmployeeQaId();
        List<EmployeeQa> allQas = employeeEntityService.findAllQas();
        assertEquals(1, allQas.size());
        employeeEntityService.deleteEntityById(createdDevEmployeeId);
        employeeEntityService.deleteEntityById(createdQaEmployeeId);
    }

    @Test
    public void testFindEmployeeQasWithHql() {
        long createdDevEmployeeId = getCreatedDevEmployeeId();
        long createdQaEmployeeId = getCreatedEmployeeQaId();
        List<EmployeeQa> allQas = employeeEntityHqlService.findAllQas();
        assertEquals(1, allQas.size());
        employeeEntityService.deleteEntityById(createdDevEmployeeId);
        employeeEntityService.deleteEntityById(createdQaEmployeeId);
    }

    @Test
    public void testFindEmployeeQasWithCriteria() {
        long createdDevEmployeeId = getCreatedDevEmployeeId();
        long createdQaEmployeeId = getCreatedEmployeeQaId();
        List<EmployeeQa> allQas = employeeEntityCriteriaService.findAllQas();
        assertEquals(1, allQas.size());
        employeeEntityService.deleteEntityById(createdDevEmployeeId);
        employeeEntityService.deleteEntityById(createdQaEmployeeId);
    }

    @Test
    public void testFindDevEmployees() {
        long createdDevEmployeeId = getCreatedDevEmployeeId();
        long createdQaEmployeeId = getCreatedEmployeeQaId();
        List<Developer> allDevs = employeeEntityService.findAllDevs();
        assertEquals(1, allDevs.size());
        employeeEntityService.deleteEntityById(createdDevEmployeeId);
        employeeEntityService.deleteEntityById(createdQaEmployeeId);
    }

    @Test
    public void testFindDevEmployeesWithHql() {
        long createdDevEmployeeId = getCreatedDevEmployeeId();
        long createdQaEmployeeId = getCreatedEmployeeQaId();
        List<Developer> allDevs = employeeEntityHqlService.findAllDevs();
        assertEquals(1, allDevs.size());
        employeeEntityService.deleteEntityById(createdDevEmployeeId);
        employeeEntityService.deleteEntityById(createdQaEmployeeId);
    }

    @Test
    public void testFindDevEmployeesWithCriteria() {
        long createdDevEmployeeId = getCreatedDevEmployeeId();
        long createdQaEmployeeId = getCreatedEmployeeQaId();
        List<Developer> allDevs = employeeEntityCriteriaService.findAllDevs();
        assertEquals(1, allDevs.size());
        employeeEntityService.deleteEntityById(createdDevEmployeeId);
        employeeEntityService.deleteEntityById(createdQaEmployeeId);
    }

    @Test
    public void testCacheFindById() {
        long createdEmployeeId = getCreatedEmployeeId();
        statistics.clear();
        employeeEntityService.findEntityById(createdEmployeeId);
        employeeEntityService.findEntityById(createdEmployeeId);
        employeeEntityService.findEntityById(createdEmployeeId);
        employeeEntityService.findEntityById(createdEmployeeId);
        assertTrue(statistics.isStatisticsEnabled());
        assertEquals(1, statistics.getSecondLevelCacheMissCount());
        assertEquals(3, statistics.getCollectionFetchCount());
        employeeEntityService.deleteEntityById(createdEmployeeId);
    }

    @Test
    public void testCacheFindByIdQueriesWithHql() {
        long createdEmployeeId = getCreatedEmployeeQaId();
        statistics.clear();
        employeeEntityHqlService.findEntityById(createdEmployeeId);
        employeeEntityHqlService.findEntityById(createdEmployeeId);
        employeeEntityHqlService.findEntityById(createdEmployeeId);
        employeeEntityHqlService.findEntityById(createdEmployeeId);
        assertTrue(statistics.isStatisticsEnabled());
        assertEquals(1, statistics.getQueryCacheMissCount());
        assertEquals(1, statistics.getQueryCachePutCount());
        assertEquals(3, statistics.getQueryCacheHitCount());
        employeeEntityHqlService.deleteEntityById(createdEmployeeId);
    }

    @Test
    public void testCacheFindByIdQueriesWithCriteria() {
        long createdEmployeeId = getCreatedDevEmployeeId();
        statistics.clear();
        employeeEntityCriteriaService.findEntityById(createdEmployeeId);
        employeeEntityCriteriaService.findEntityById(createdEmployeeId);
        employeeEntityCriteriaService.findEntityById(createdEmployeeId);
        employeeEntityCriteriaService.findEntityById(createdEmployeeId);
        assertTrue(statistics.isStatisticsEnabled());
        assertEquals(1, statistics.getQueryCacheMissCount());
        assertEquals(1, statistics.getQueryCachePutCount());
        assertEquals(3, statistics.getQueryCacheHitCount());
        employeeEntityService.deleteEntityById(createdEmployeeId);
    }

    private long getCreatedEmployeeId() {
        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo(FIRST_NAME, LAST_NAME, AGE, PHONE_NUMBER);
        Address addressModel = new Address(CITY, STREET, HOUSE, APARTMENT);

        return employeeEntityService.createEmployee(personalInfo, addressModel, EmployeeStatus.ACTIVE);
    }

    private long getCreatedEmployeeQaId() {
        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo(FIRST_NAME, LAST_NAME, AGE, PHONE_NUMBER);
        Address addressModel = new Address(CITY, STREET, HOUSE, APARTMENT);

        return employeeEntityService.createEmployeeQa(personalInfo, addressModel, EmployeeStatus.ACTIVE);
    }

    private long getCreatedDevEmployeeId() {
        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo(FIRST_NAME, LAST_NAME, AGE, PHONE_NUMBER);
        Address address = new Address(CITY, STREET, HOUSE, APARTMENT);

        return employeeEntityService.createDeveloperEmployee(personalInfo, address, EmployeeStatus.ACTIVE);
    }

}
