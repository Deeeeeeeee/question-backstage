package com.seal_de.test;

import com.seal_de.data.PaperRepository;
import com.seal_de.data.TaskRepository;
import com.seal_de.domain.Paper;
import com.seal_de.domain.Task;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by sealde on 4/25/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@ContextConfiguration(classes=RepositoryTestConfig.class)
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PaperRepository paperRepository;

    @Test
    @Transactional
    public void save() {
        Task task = createTask1();
        taskRepository.saveOrUpdate(task);
    }

    @Test
    @Transactional
    public void findByUserId() {
        List<Task> tasks = taskRepository.findByUserId("12");
        System.out.println(tasks);
    }

    private Task createTask1() {
        Task task = new Task();
        Paper paper = paperRepository.getById("ff8081815ba54ace015ba54ad0b90000");
        task.setId("random id");
        task.setUserId("12");
        task.setCreateTime(new Date());
        task.setStatus(20);
        task.setPaperId(paper);
        return task;
    }
}
