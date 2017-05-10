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

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

        PaperDetail paperDetail = initPaperDetail(paper);
        paperDetailService.save(paperDetail);

        final String taskId = task.getId();
        return new HashMap<String, String>(){{this.put("taskId", taskId);}};
    }

    private Task processTask(String userId, Paper paper) {
        Task task = new Task();
        task.setUserId(userId);
        task.setPaperId(paper);
        return task;
    }

    private PaperDetail initPaperDetail(Paper paper) {
        PaperDetail paperDetail = new PaperDetail();
        paperDetail.setPaperId(paper.getId());
        paperDetail.setParentIndex(0);
        paperDetail.setPaperItems(initPaperItems());
        return paperDetail;
    }

    private List<PaperItem> initPaperItems() {
        final PaperItem paperItem = new PaperItem();
        paperItem.setChildIndex(0);
        return new ArrayList<PaperItem>(){{add(paperItem);}};
    }

    @RequestMapping(value = "/editPaper/{taskId}", method = RequestMethod.GET)
    public PaperInfoModel editPaper(@PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        Paper paper = task.getPaperId();

        List<PaperDetail> details = paperDetailService.findByPaperId(paper.getId());
        return new PaperInfoModel(details);
    }

    @RequestMapping(value = "/addOrUpdatePaperDetail/{taskId}", method = RequestMethod.POST)
    public PaperInfoModel addOrUpdatePaperDetail(@RequestBody PaperDetail paperDetail, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String paperId = task.getPaperId().getId();
        verifyStatus(task);

        PaperDetail persistenceDetail = paperDetailService.getByPaperIdAndParentId(paperId, paperDetail.getParentIndex());
        if(persistenceDetail != null)
            paperDetail.setId(persistenceDetail.getId());

        paperDetail.setPaperId(paperId);
        paperDetailService.saveAfterClear(paperDetail);

        List<PaperDetail> paperDetails = paperDetailService.findByPaperId(paperId);
        return new PaperInfoModel(paperDetails);
    }

    @RequestMapping(value = "/deletePaperDetail/{taskId}", method = RequestMethod.POST)
    public PaperInfoModel deletePaperDetail(@RequestParam Integer parentIndex, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String paperId = task.getPaperId().getId();
        verifyStatus(task);

        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentId(paperId, parentIndex);
        paperDetailService.verifyDeletePaperDetail(paperDetail);

        paperDetailService.delete(paperDetail);

        List<PaperDetail> paperDetails = paperDetailService.findByPaperId(paperId);
        paperDetails = paperDetailService.reduceParentIndex(paperDetails, parentIndex);
        return new PaperInfoModel(paperDetails);
    }

    @RequestMapping(value = "/addOrUpdatePaperItem/{taskId}", method = RequestMethod.POST)
    public PaperInfoModel addOrUpdatePaperItem(@RequestBody PaperItemInfoModel paperItemInfoModel, @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String paperId = task.getPaperId().getId();
        String paperDetailId = getDetailId(paperItemInfoModel, paperId);
        PaperItem paperItem = paperItemInfoModel.getPaperItem();
        verifyStatus(task);

        PaperItem persistenceItem = paperItemService.getByPaperDetailIdAndChildIndex(
                paperDetailId, paperItem.getChildIndex());
        if(persistenceItem != null)
            paperItem.setId(persistenceItem.getId());

        paperItem.setPaperDetailId(paperDetailId);
        paperItemService.saveAfterClear(paperItem);

        List<PaperDetail> paperDetails = paperDetailService.findByPaperId(paperId);
        return new PaperInfoModel(paperDetails);
    }

    private String getDetailId(PaperItemInfoModel paperItemInfoModel, String paperId) {
        Integer parentIndex = paperItemInfoModel.getParentIndex();
        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentId(paperId, parentIndex);
        return paperDetail.getId();
    }

    @RequestMapping(value = "/deletePaperItem/{taskId}", method = RequestMethod.POST)
    public PaperInfoModel deletePaperItem(@RequestParam Integer parentIndex, @RequestParam Integer childIndex,
                                  @PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        String paperId = task.getPaperId().getId();
        verifyStatus(task);

        PaperDetail paperDetail = paperDetailService.getByPaperIdAndParentId(paperId, parentIndex);
        PaperItem paperItem = paperItemService.getByPaperDetailIdAndChildIndex(paperDetail.getId(), childIndex);

        paperItemService.delete(paperItem);
        notNull(paperItem, HttpStatus.NOT_FOUND, "删除错误：没有这道小题");

        List<PaperItem> paperItems = paperItemService.findByPaperDetailId(paperDetail.getId());
        paperItemService.reduceChildIndex(paperItems, childIndex);

        List<PaperDetail> paperDetails = paperDetailService.findByPaperIdAfterClear(paperId);
        return new PaperInfoModel(paperDetails);
    }

    @PostMapping(value = "/finishEditPaper/{taskId}")
    public String finishEditPaper(@PathVariable String taskId) {
        Task task = taskService.getById(taskId);
        task.setStatus(20);
        taskService.save(task);
        return "success";
    }

    @PostMapping(value = "/saveErrorMsg/{taskId}")
    public String saveErrorMsg(UserInfo user, @PathVariable String taskId, @RequestParam String errorMsg) {
        Task task = taskService.getById(taskId);
        boolean isAuditor = user.getRole().intValue() == 2;
        isTrue(isAuditor, HttpStatus.BAD_REQUEST, "权限不足：不是审核者");

        task.setStatus(30);
        task.setErrorMessage(errorMsg);
        taskService.save(task);
        return "success";
    }

    private void verifyStatus(Task task) {
        Integer status = task.getStatus();
        boolean isEditPermit = (status >= 10 && status < 20) || (status >= 30 && status < 40);
        isTrue(isEditPermit, HttpStatus.BAD_REQUEST,
                "非法操作：现在不允许修改试卷");
    }
}
