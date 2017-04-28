package com.seal_de.controller;

import com.seal_de.domain.Paper;
import com.seal_de.domain.UserInfo;
import com.seal_de.service.PaperService;
import com.seal_de.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.seal_de.service.util.VerifyUtil.notNull;

/**
 * Created by sealde on 4/29/17.
 */
@RestController
@RequestMapping("/paper")
public class PaperController {
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

    @RequestMapping(value = "/basicInfo", method = RequestMethod.GET)
    public Paper basicInfo(String paperId) {
        Paper paper = paperService.getById(paperId);
        return paper;
    }
}
