package com.epam.cdp.module3.homework4.domain.enumeration;

public enum EmployeeStatus {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String name;

    EmployeeStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
