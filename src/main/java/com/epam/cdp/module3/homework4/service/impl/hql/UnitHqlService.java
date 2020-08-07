package com.epam.cdp.module3.homework4.service.impl.hql;

import com.epam.cdp.module3.homework4.dao.hql.EntityHqlDao;
import com.epam.cdp.module3.homework4.domain.Unit;
import com.epam.cdp.module3.homework4.service.AbstractEntityHqlService;
import com.epam.cdp.module3.homework4.service.UnitService;

public class UnitHqlService extends AbstractEntityHqlService<Unit> implements UnitService {

    private EntityHqlDao<Unit> unitHqlDao;

    public UnitHqlService(EntityHqlDao<Unit> unitHqlDao) {
        this.unitHqlDao = unitHqlDao;
    }

    @Override
    protected EntityHqlDao<Unit> getEntityHqlDao() {
        return unitHqlDao;
    }

    @Override
    public Long createUnit(String name) {
        return null;
    }
}
