package com.epam.cdp.module3.homework4.service.impl;

import com.epam.cdp.module3.homework4.dao.EntityDao;
import com.epam.cdp.module3.homework4.domain.Unit;
import com.epam.cdp.module3.homework4.service.AbstractEntityService;
import com.epam.cdp.module3.homework4.service.UnitService;

public class UnitServiceImpl extends AbstractEntityService<Unit> implements UnitService {

    private EntityDao<Unit> unitDao;

    public UnitServiceImpl(EntityDao<Unit> unitDao) {
        this.unitDao = unitDao;
    }

    @Override
    public Long createUnit(String name) {
        return createEntity(new Unit(name));
    }

    @Override
    public void updateEntityById(Long unitId, Unit entity) {
        entity.setUnitId(unitId);
        updateEntity(entity);
    }

    @Override
    public EntityDao<Unit> getEntityDao() {
        return unitDao;
    }
}
