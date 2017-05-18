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
                .list();
    }

    public List<Task> findByStatus(Integer status) {
        return createCriteria()
                .add(Restrictions.eq("status", status))
                .addOrder(Order.asc("createTime"))
                .list();
    }

    public Task getByStatus(Integer status) {
        return (Task) createCriteria()
                .add(Restrictions.eq("status", status))
                .addOrder(Order.asc("createTime"))
                .uniqueResult();
    }

    public Task getByAuditorIdAndStatus(String auditorId, Integer status) {
        return (Task) createCriteria()
                .add(Restrictions.eq("auditorId", auditorId))
                .add(Restrictions.eq("status", status))
                .uniqueResult();
    }

    public List<Task> findByAuditorId(String auditorId) {
        return createCriteria()
                .add(Restrictions.eq("auditorId", auditorId))
                .list();
    }

    public Task getByUserIdAndStatus(String userId, Integer status) {
        return (Task) createCriteria()
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("status", status))
                .uniqueResult();
    }
}
