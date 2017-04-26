package com.seal_de.data.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by sealde on 4/24/17.
 */
public abstract class AbstractRepository<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public AbstractRepository() {}
    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(T element) {
        currentSession().saveOrUpdate(element);
    }

    public T getById(Serializable id) {
        return (T) currentSession().load(getClazz(), id);
    }

    public void delete(T element) {
        currentSession().delete(element);
    }

    public Criteria createCriteria() {
        return currentSession()
                .createCriteria(getClazz());
    }

    private Class<T> getClazz() {
        return  (Class<T>)((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
