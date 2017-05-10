package com.seal_de.data.dao;

import com.seal_de.data.TaskRepository;
import com.seal_de.domain.Task;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sealde on 4/24/17.
 */
@Repository
public class HibernateTaskRepository extends AbstractRepository<Task> implements TaskRepository {
    public List<Task> findByUserId(String userId) {
        return createCriteria()
                .add(Restrictions.eq("userId", userId))
                .addOrder(Order.asc("id"))
                .list();
    }

    public List<Task> findByStatus(Integer status) {
        return createCriteria()
                .add(Restrictions.eq("status", status))
                .addOrder(Order.asc("createTime"))
                .list();
    }
}
