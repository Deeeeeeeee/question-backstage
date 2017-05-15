package com.seal_de.service.impl;

import com.seal_de.data.IRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sealde on 4/25/17.
 */
public abstract class AbstractServiceImpl<K extends IRepository, E> {
    protected K repository;

    @Transactional
    public boolean save(E element) {
        repository.saveOrUpdate(element);
        return true;
    }

    @Transactional
    public boolean save(List<E> list, int index) {
        ListIterator<E> it = list.listIterator();
        while(it.hasNext()) {
            repository.saveOrUpdate(it.next());
        }
        return true;
    }

    @Transactional
    public boolean saveAfterClear(E element){
        repository.clear();
        repository.saveOrUpdate(element);
        return true;
    }

    @Transactional
    public void delete(E element) {
        repository.delete(element);
    }

    @Transactional
    public void deleteAfter(E element) {
        repository.clear();
        repository.delete(element);
    }

    @Transactional
    public E getById(Serializable id) {
        return (E) repository.getById(id);
    }
}
