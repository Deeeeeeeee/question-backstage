package com.seal_de.test;

import com.seal_de.controller.ExceptionRestController;
import com.seal_de.controller.HomeController;
import com.seal_de.domain.UserInfo;
import com.seal_de.exception.NotFoundException;
import com.seal_de.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class HomeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserInfoService userInfoService;

    @Mock
    private NotFoundException notFoundException;

    @InjectMocks
    private HomeController controller;

    @InjectMocks
    private ExceptionRestController exceptionRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller, exceptionRestController).build();
        when(userInfoService.saveUserInfo(createUserInfoS())).thenReturn(true);
        when(userInfoService.saveUserInfo(createUserInfoF())).thenReturn(false);
        when(userInfoService.getByUsername(eq("22"))).thenReturn(createUserInfoS());
    }

    @Test
    public void testHomePage() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post("/register")
                .param("id", "1")
                .param("username", "22")
                .param("password", "33")
                .param("role", "4")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "22")
                .param("password", "44")
                )
                .andDo(print())
                .andExpect(content().bytes("false".getBytes()));
    }

    @Test
    public void testException() throws Exception {
        mockMvc.perform(get("/fileupload"))
                .andDo(print());
    }

    private UserInfo createUserInfoS() {
        UserInfo userInfo  = new UserInfo();
        userInfo.setId("1");
        userInfo.setUsername("22");
        userInfo.setPassword("33");
        userInfo.setRole(4);
        return userInfo;
    }

    private UserInfo createUserInfoF() {
        UserInfo userInfo  = new UserInfo();
        userInfo.setId("2");
        userInfo.setUsername("22");
        userInfo.setPassword("33");
        userInfo.setRole(4);
        return userInfo;
    }

}
