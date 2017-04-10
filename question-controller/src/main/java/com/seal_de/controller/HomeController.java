package com.seal_de.controller;

import com.seal_de.domain.UserInfo;
import static com.seal_de.exception.VerifyUtil.*;
import com.seal_de.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    private UserInfoService userInfoService;

    public HomeController(){}

    @Autowired
    public HomeController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Autowired
    private HttpServletRequest request;

    @RequestMapping
    public String home(){
        return "home";
    }

    @RequestMapping(value = "fileupload", method = RequestMethod.GET)
    public String fileupload(){
        return "uploadForm";
    }

    @ResponseBody
    @RequestMapping(value = "register")
    public String register(@RequestBody UserInfo userInfo) {
        userInfoService.saveUserInfo(userInfo);
        return "register success";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody UserInfo userInfo) {
        UserInfo user = userInfoService.getByUsername(userInfo.getUsername());
        notNull(user, HttpStatus.NOT_FOUND, "用户不存在");
        stringEquals(user.getPassword(), userInfo.getPassword(),
                HttpStatus.UNAUTHORIZED, "用户名或密码不正确");
        return "success";
    }

}
