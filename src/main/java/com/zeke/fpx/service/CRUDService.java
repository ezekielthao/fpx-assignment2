package com.zeke.fpx.service;

import java.util.List;

/**
 * Created by Zeke on 6/20/2016.
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(String id);

    T saveOrUpdate(T domainObject);

    void delete(String id);
}
