package com.seal_de.data.dao;


import com.seal_de.data.CitiesRepository;
import com.seal_de.domain.Cities;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateCitiesRepository implements CitiesRepository{
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateCitiesRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<Cities> findByProvinceId(String provinceId) {
        return citiesCriteria()
                .add(Restrictions.eq("provinceId", provinceId))
                .list();
    }

    private Criteria citiesCriteria(){
        return currentSession()
                .createCriteria(Cities.class)
                .addOrder(Order.asc("id"));
    }
}
