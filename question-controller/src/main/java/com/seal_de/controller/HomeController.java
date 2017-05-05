package com.seal_de.controller;

import com.seal_de.domain.UserInfo;
import static com.seal_de.service.util.VerifyUtil.*;

import com.seal_de.security.TokenManager;
import com.seal_de.service.UserInfoService;
import com.seal_de.service.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private TokenManager tokenManager;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping
    public String home(){
        return "home";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "fileupload", method = RequestMethod.GET)
    public String fileupload(){
        return "uploadForm";
    }

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@RequestBody UserInfo userInfo) {
        userInfoService.saveUserInfo(userInfo);
        return "register success";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public RoleToken login(@RequestBody UserInfo userInfo) {
        String username = userInfo.getUsername();
        UserInfo user = userInfoService.getByUsername(username);

        notNull(user, HttpStatus.NOT_FOUND, "用户不存在");
        isTrue(EncryptUtil.matchs(userInfo.getPassword(), user.getPassword()),
                HttpStatus.UNAUTHORIZED, "用户名或密码不正确");

        Integer role = user.getRole();
        String token = tokenManager.createToken(username);
        return new RoleToken(role, token);
    }

    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(){
        String token = request.getHeader("Access-Control-Allow-Headers:authorization");
        tokenManager.removeToken(token);
        return "logout success";
    }

    private class RoleToken {
        private Integer role;
        private String token;

        public RoleToken(Integer role, String token) {
            this.role = role;
            this.token = token;
        }

        public Integer getRole() {
            return role;
        }

        public String getToken() {
            return token;
        }
    }
}
