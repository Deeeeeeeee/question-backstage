package com.seal_de.data;

import com.seal_de.domain.Task;

import java.util.List;

/**
 * Created by sealde on 4/24/17.
 */
public interface TaskRepository extends IRepository<Task>{
    List<Task> findByUserId(String userId);
    List<Task> findByStatus(Integer status);
}
