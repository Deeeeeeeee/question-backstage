package com.seal_de.test;

import com.seal_de.data.UserInfoRepository;
import com.seal_de.domain.UserInfo;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryTestConfig.class)
public class UserInfoRepositoryTest {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    @Transactional
    public void saveUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("5");
        userInfo.setUsername("lalala");
        userInfo.setPassword("lalalala");
        userInfoRepository.save(userInfo);
    }
}
