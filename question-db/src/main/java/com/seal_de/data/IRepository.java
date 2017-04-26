package com.seal_de.data;

import java.io.Serializable;

/**
 * Created by sealde on 4/26/17.
 */
public interface IRepository<T> {
    void saveOrUpdate(T element);
    T getById(Serializable id);
    void delete(T element);
}
