package com.epam.cdp.module3.homework4.dao.hql;

import com.epam.cdp.module3.homework4.domain.Unit;

public class UnitHqlDao extends EntityHqlDao<Unit> {

    @Override
    public Class<Unit> getEntityClass() {
        return Unit.class;
    }
}
