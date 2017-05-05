package com.seal_de.controller;

import com.seal_de.Model.PaperInfoModel;
import com.seal_de.Model.PaperItemInfoModel;
import com.seal_de.Model.TaskInfoModel;
import com.seal_de.domain.*;
import com.seal_de.service.*;

import static com.seal_de.service.util.VerifyUtil.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private PaperDetailService paperDetailService;
    @Autowired
    private PaperItemService paperItemService;

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
        final String taskId = paper.getId();
        return new HashMap<String, String>(){{this.put("taskId", taskId);}};
    }

    private Task processTask(String userId, Paper paper) {
        Task task = new Task();
        task.setUserId(userId);
        task.setPaperId(paper);
        return task;
    }

    @RequestMapping(value = "/editPaper/{taskId}", method = RequestMethod.GET)
    public PaperInfoModel editPaper(@PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        Paper paper = task.getPaperId();

        List<PaperDetail> details = paperDetailService.findByPaperId(paper.getId());
        return new PaperInfoModel(paper, null, details);
    }

    @RequestMapping(value = "/addPaperDetail/{taskId}", method = RequestMethod.POST)
    public String addPaperDetail(@RequestBody PaperDetail paperDetail, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        Paper paper = task.getPaperId();

        paperDetail.setPaperId(paper.getId());
        paperDetailService.save(paperDetail);
        return "success";
    }

    @RequestMapping(value = "/deletePaperDetail/{taskId}", method = RequestMethod.POST)
    public String deletePaperDetail(@RequestBody String parentIndex, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        Paper paper = task.getPaperId();

        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentId(paper.getId(), parentIndex);

        List<PaperItem> paperItems = paperDetail.getPaperItems();
        isNull(paperItems, HttpStatus.FORBIDDEN, "请删除完小题再删除大题");

        paperDetailService.delete(paperDetail);
        return "success";
    }

    @RequestMapping(value = "/addPaperItem/{taskId}", method = RequestMethod.POST)
    public String addPaperItem(@RequestBody PaperItemInfoModel paperItemInfoModel, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        Paper paper = task.getPaperId();

        String parentIndex = paperItemInfoModel.getParentIndex();
        PaperItem paperItem = paperItemInfoModel.getPaperItem();
        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentId(paper.getId(), parentIndex);

        paperItem.setPaperDetailId(paperDetail.getId());
        paperItemService.save(paperItem);
        return "success";
    }

    @RequestMapping(value = "/deletePaperItem/{taskId}", method = RequestMethod.POST)
    public String deletePaperItem(@RequestParam String parentIndex, @RequestParam String childIndex,
                                  @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        Paper paper = task.getPaperId();

        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentId(paper.getId(), parentIndex);
        PaperItem paperItem = paperItemService.getByPaperDetailIdAndChildIndex(paperDetail.getId(), childIndex);

        paperItemService.delete(paperItem);
        return "success";
    }
}
