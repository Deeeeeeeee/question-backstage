package com.seal_de.controller;

import com.seal_de.Model.TaskInfoModel;
import com.seal_de.domain.Paper;
import com.seal_de.domain.Task;
import com.seal_de.domain.UserInfo;
import com.seal_de.service.PaperService;
import com.seal_de.service.TaskService;
import com.seal_de.service.UserInfoService;
import static com.seal_de.service.util.VerifyUtil.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sealde on 4/24/17.
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PaperService paperService;

    @ModelAttribute
    public UserInfo pre(@RequestAttribute String token_username) {
        UserInfo user = userInfoService.getByUsername(token_username);
        notNull(user, HttpStatus.NOT_FOUND, "用户不存在");
        return user;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TaskInfoModel> taskList(UserInfo user) {
        List<Task> tasks = taskService.findByUserId(user.getId());
        List<TaskInfoModel> taskInfoModels = taskService.taskToTaskInfoModel(tasks);
        return taskInfoModels;
    }

    @RequestMapping(value = "/startMaking", method = RequestMethod.POST)
    public Map<String, String> startMaking(UserInfo user, @RequestBody Paper paper) {
        Task task = processTask(user.getId(), paper);
        paperService.save(paper);
        taskService.save(task);
        final String paperId = paper.getId();
        return new HashMap<String, String>(){{this.put("paperId", paperId);}};
    }

    private Task processTask(String userId, Paper paper) {
        Task task = new Task();
        task.setUserId(userId);
        task.setPaperId(paper);
        return task;
    }
}
