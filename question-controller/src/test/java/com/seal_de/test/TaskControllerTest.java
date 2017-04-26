package com.seal_de.test;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.seal_de.controller.TaskController;
import com.seal_de.domain.UserInfo;
import com.seal_de.service.PaperService;
import com.seal_de.service.TaskService;
import com.seal_de.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by sealde on 4/26/17.
 */
public class TaskControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserInfoService userInfoService;
    @Mock
    private PaperService paperService;
    @Mock
    private TaskService taskService;
    @InjectMocks
    private TaskController controller;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).build();
        when(userInfoService.getByUsername("jm1")).thenReturn(createUserJm1());
    }

    @Test
    public void testStartMaking() throws Exception {
        mockMvc.perform(get("/task/startMaking")
                .param("grade", "初中二年级")
                .param("paperName", "初二期末linux考试")
                .param("paperType", "2")
                .param("province", "广东省")
                .param("school", "广东工业中学")
                .param("year", "2017/1/1")
                .param("subject", "黑客")
                .requestAttr("token_username", "jm1")
                )
                .andDo(print());
    }

    private UserInfo createUserJm1() {
        UserInfo user = new UserInfo();
        user.setId("12");
        user.setPassword("123");
        user.setUsername("jm1");
        user.setRole(1);
        return user;
    }
}
