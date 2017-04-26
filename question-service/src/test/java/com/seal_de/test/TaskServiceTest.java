package com.seal_de.test;

import com.seal_de.domain.Paper;
import com.seal_de.domain.Task;
import com.seal_de.service.TaskService;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import com.seal_de.test.testConfig.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by sealde on 4/26/17.
 */
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RepositoryTestConfig.class, RootConfig.class})
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void findByUserId() {
        List<Task> tasks = taskService.findByUserId("12");
        System.out.println(tasks);
    }

    @Test
    public void save() {
        Task task = createTask1();
        taskService.save(task);
    }

    private Task createTask1() {
        Task task = new Task();
//        task.setId("random id");
        task.setUserId("12");
        task.setCreateTime(new Date());
        task.setStatus(20);
        return task;
    }
}
