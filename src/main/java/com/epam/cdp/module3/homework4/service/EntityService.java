package com.epam.cdp.module3.homework4.service;

import java.util.Optional;

public interface EntityService<T> {

    Optional<T> findEntityById(long id);

    void updateEntityById(Long entityId, T entity);

    void deleteEntityById(long id);

    Long createEntity(T entity);

}
