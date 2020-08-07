package com.epam.cdp.module3.homework4.service;

import com.epam.cdp.module3.homework4.domain.Unit;

public interface UnitService extends EntityService<Unit> {

    Long createUnit(String name);

}
