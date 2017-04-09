package com.seal_de.test;

import com.seal_de.domain.UserInfo;
import com.seal_de.service.UserInfoService;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import com.seal_de.test.testConfig.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RepositoryTestConfig.class, RootConfig.class})
public class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void saveUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId("2");
        userInfo.setUsername("hh");
        userInfo.setPassword("qq");
        userInfoService.saveUserInfo(userInfo);
    }
}
