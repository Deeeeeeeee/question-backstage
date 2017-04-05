package com.seal_de.test;

import com.seal_de.data.UserRepository;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RepositoryTestConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void getByUsername(){
        Assert.assertEquals("ccc", userRepository.getByUsername("aaa").getPassword());
    }

}
