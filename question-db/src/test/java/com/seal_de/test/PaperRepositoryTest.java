package com.seal_de.test;

import com.seal_de.data.PaperRepository;
import com.seal_de.domain.Paper;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by sealde on 4/25/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@ContextConfiguration(classes=RepositoryTestConfig.class)
public class PaperRepositoryTest {
    @Autowired
    private PaperRepository paperRepository;

    @Test
    @Transactional
    public void save() {
        Paper paper = createPaper1();
        paperRepository.saveOrUpdate(paper);
    }

    @Test
    @Transactional
    public void get() {
        Paper paper = paperRepository.getById("ff8081815ba54ace015ba54ad0b90000");
        System.out.println(paper);
    }

    private Paper createPaper1() {
        Paper paper = new Paper();
//        paper.setId("random id");
        paper.setGrade("初二");
        paper.setPaperName("初二期末linux考试");
        paper.setPaperType("2");
        paper.setRegion("广东省");
        paper.setSchool("广东工业中学");
        paper.setSubject("黑客");
        paper.setYear("2017");
        return paper;
    }
}
