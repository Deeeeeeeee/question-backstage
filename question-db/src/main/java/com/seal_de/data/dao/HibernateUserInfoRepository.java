package com.seal_de.data.dao;

import com.seal_de.data.UserInfoRepository;
import com.seal_de.domain.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateUserInfoRepository implements UserInfoRepository{
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateUserInfoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public UserInfo getByUsername(String username) {
        return (UserInfo) userInfoCriteria()
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    public boolean save(UserInfo userInfo) {
        currentSession().save(userInfo);
        return true;
    }

    private Criteria userInfoCriteria(){
        return currentSession()
                .createCriteria(UserInfo.class)
                .addOrder(Order.asc("id"));
    }
}
