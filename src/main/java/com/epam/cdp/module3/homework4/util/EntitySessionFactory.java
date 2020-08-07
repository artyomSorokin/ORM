package com.epam.cdp.module3.homework4.util;

import com.epam.cdp.module3.homework4.domain.Developer;
import com.epam.cdp.module3.homework4.domain.Employee;
import com.epam.cdp.module3.homework4.domain.EmployeePersonalInfo;
import com.epam.cdp.module3.homework4.domain.EmployeeQa;
import com.epam.cdp.module3.homework4.domain.Project;
import com.epam.cdp.module3.homework4.domain.Unit;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class EntitySessionFactory {

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Developer.class)
                .addAnnotatedClass(EmployeeQa.class)
                .addAnnotatedClass(EmployeePersonalInfo.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Unit.class);
        System.out.println("Hibernate Annotation Configuration loaded");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        System.out.println("Hibernate Annotation serviceRegistry created");

        return configuration.buildSessionFactory(serviceRegistry);
    }
    /**
     * create new session or return current.
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }
}
