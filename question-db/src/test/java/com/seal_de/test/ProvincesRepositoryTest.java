package com.seal_de.test;

import com.seal_de.data.ProvincesRepository;
import com.seal_de.domain.Provinces;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RepositoryTestConfig.class)
public class ProvincesRepositoryTest {
    @Autowired
    private ProvincesRepository provincesRepository;

    @Test
    @Transactional
    public void findAll(){
        List<Provinces> list =  provincesRepository.findAll();
        System.out.println(list.size());
    }
}
