package com.seal_de.test;

import com.seal_de.data.PaperDetailRepository;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sealde on 5/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@ContextConfiguration(classes=RepositoryTestConfig.class)
public class PaperDetailRepositoryTest {
    @Autowired
    private PaperDetailRepository paperDetailRepository;

    @Test
    @Transactional
    public void save() {
        PaperDetail paperDetail = createPaperDetail1();
        paperDetailRepository.saveOrUpdate(paperDetail);
    }

    @Test
    @Transactional
    public void saveCascade() {
        PaperDetail paperDetail = createPaperDetail2();
        paperDetailRepository.saveOrUpdate(paperDetail);
    }

    private PaperDetail createPaperDetail1() {
        PaperDetail paperDetail = new PaperDetail();
        paperDetail.setPaperId("16");
        paperDetail.setQuestionType("选择题");
        paperDetail.setParentIndex(1);
        return paperDetail;
    }

    private PaperDetail createPaperDetail2() {
        PaperDetail paperDetail = new PaperDetail();
        paperDetail.setPaperId("16");
        paperDetail.setQuestionType("计算题");
        paperDetail.setParentIndex(2);
        paperDetail.setPaperItems(createPaperItems());
        return paperDetail;
    }

    private List<PaperItem> createPaperItems() {
        final PaperItem paperItem = new PaperItem();
        paperItem.setChildIndex(0);
        return new ArrayList<PaperItem>(){{add(paperItem);}};
    }

    @Test
    @Transactional
    public void findByPaperId() {
        List<PaperDetail> paperDetails = paperDetailRepository.findByPaperId("16");
        System.out.println(paperDetails);
    }
}
