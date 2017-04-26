package com.seal_de.service;

import com.seal_de.domain.Task;

import java.util.List;

/**
 * Created by sealde on 4/24/17.
 */
public interface TaskService extends IService<Task>{
    List<Task> findByUserId(String userId);
}
