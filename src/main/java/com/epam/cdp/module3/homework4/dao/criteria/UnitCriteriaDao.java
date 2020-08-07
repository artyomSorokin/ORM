package com.epam.cdp.module3.homework4.dao.criteria;

import com.epam.cdp.module3.homework4.domain.Unit;

public class UnitCriteriaDao extends EntityCriteriaDao<Unit> {

    @Override
    public Class<Unit> getEntityClass() {
        return Unit.class;
    }

    @Override
    public String getIdName() {
        return "unitId";
    }
}
