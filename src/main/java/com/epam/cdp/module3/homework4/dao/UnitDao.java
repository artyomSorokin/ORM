package com.epam.cdp.module3.homework4.dao;

import com.epam.cdp.module3.homework4.domain.Unit;

public class UnitDao extends EntityDao<Unit> {

    @Override
    public Class<Unit> getEntityClass() {
        return Unit.class;
    }
}
