package com.epam.cdp.module3.homework4.service.impl.criteria;

import com.epam.cdp.module3.homework4.dao.criteria.EntityCriteriaDao;
import com.epam.cdp.module3.homework4.domain.Unit;
import com.epam.cdp.module3.homework4.service.AbstractEntityCriteriaService;
import com.epam.cdp.module3.homework4.service.UnitService;

public class UnitCriteriaService extends AbstractEntityCriteriaService<Unit> implements UnitService {

    private EntityCriteriaDao<Unit> unitCriteriaDao;

    public UnitCriteriaService(EntityCriteriaDao<Unit> unitCriteriaDao) {
        this.unitCriteriaDao = unitCriteriaDao;
    }

    @Override
    public Long createUnit(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityCriteriaDao<Unit> getCriteriaDao() {
        return unitCriteriaDao;
    }

}
