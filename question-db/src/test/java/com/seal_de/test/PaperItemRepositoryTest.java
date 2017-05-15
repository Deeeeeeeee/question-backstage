package com.seal_de.test;

import com.seal_de.data.PaperDetailRepository;
import com.seal_de.data.PaperItemRepository;
import com.seal_de.domain.PaperDetail;
import com.seal_de.domain.PaperItem;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by sealde on 5/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@ContextConfiguration(classes=RepositoryTestConfig.class)
public class PaperItemRepositoryTest {
    @Autowired
    private PaperItemRepository paperItemRepository;
    @Autowired
    private PaperDetailRepository paperDetailRepository;

    @Test
    @Transactional
    public void save() {
        PaperItem paperItem1 = createPaperItem1();
//        PaperItem paperItem2 = createPaperItem2();
        paperItemRepository.saveOrUpdate(paperItem1);
//        paperItemRepository.saveOrUpdate(paperItem2);
    }

    @Test
    @Transactional
    public void delete() {
        PaperDetail paperDetail = paperDetailRepository.getById("ff8081815c06b949015c06be4e870005");
        PaperItem paperItem = paperItemRepository.getById("ff8081815c0d2e90015c0d2f2e940003");
        paperItemRepository.clear();
        paperItemRepository.delete(paperItem);
        System.out.println("success");
    }

    private PaperItem createPaperItem1() {
        PaperItem paperItem = new PaperItem();
        paperItem.setStem("这是一道无解的题");
        paperItem.setAnswer("没有答案");
        paperItem.setChildIndex(1);
        paperItem.setExamPoint("当你遇到无解的题");
        paperItem.setSolution("坐等交卷");
//        paperItem.setPaperDetailId(createPaperDetail1());
        return paperItem;
    }

    private PaperItem createPaperItem2() {
        PaperItem paperItem = new PaperItem();
        paperItem.setStem("这是一道无解的题");
        paperItem.setAnswer("没有答案");
        paperItem.setChildIndex(2);
        paperItem.setExamPoint("当你遇到无解的题");
        paperItem.setSolution("坐等交卷");
//        paperItem.setPaperDetailId("ff8081815bd67f0b015bd67f0f2a0000");
        return paperItem;
    }
}
