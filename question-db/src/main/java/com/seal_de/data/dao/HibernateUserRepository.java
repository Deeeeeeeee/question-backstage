package com.seal_de.data.dao;


import com.seal_de.data.UserRepository;
import com.seal_de.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserRepository implements UserRepository{
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public User getByUsername(String username) {
        return (User) currentSession().createQuery("from User where username=:p1").setString("p1", username).iterate().next();
    }
}
