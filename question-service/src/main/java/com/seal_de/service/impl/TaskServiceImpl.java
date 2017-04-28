package com.seal_de.service.impl;

import com.seal_de.Model.TaskInfoModel;
import com.seal_de.data.TaskRepository;
import com.seal_de.domain.Paper;
import com.seal_de.domain.Task;
import com.seal_de.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sealde on 4/24/17.
 */
@Service
public class TaskServiceImpl extends AbstractServiceImpl<TaskRepository, Task> implements TaskService {
    protected TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    @Override
    @Transactional
    public boolean save(Task task) {
        String id = task.getId();
        if(id == null || "".equals(id)) {
            task.setId(null);
            task.setCreateTime(new Date());
            task.setStatus(10);
        }
        repository.saveOrUpdate(task);
        return true;
    }

    @Transactional
    public List<Task> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public List<TaskInfoModel> taskToTaskInfoModel(List<Task> tasks) {
        List<TaskInfoModel> taskInfoModels = new ArrayList<TaskInfoModel>();
        for(Task task : tasks) {
            TaskInfoModel taskInfoModel = new TaskInfoModel();
            Paper paper = task.getPaperId();

            taskInfoModel.setStatus(task.getStatus());
            taskInfoModel.setCreateTime(task.getCreateTime());
            taskInfoModel.setGrade(paper.getGrade());
            taskInfoModel.setSubject(paper.getSubject());
            taskInfoModel.setPaperName(paper.getPaperName());
            taskInfoModels.add(taskInfoModel);
        }
        return taskInfoModels;
    }
}
