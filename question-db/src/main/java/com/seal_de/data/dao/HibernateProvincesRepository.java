package com.seal_de.data.dao;

import com.seal_de.data.ProvincesRepository;
import com.seal_de.domain.Provinces;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateProvincesRepository extends AbstractRepository<Provinces> implements ProvincesRepository{

    public List<Provinces> findAll(){
        return createCriteria()
                .addOrder(Order.asc("id"))
                .list();
    }

}
