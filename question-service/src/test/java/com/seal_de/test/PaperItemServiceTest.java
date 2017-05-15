package com.seal_de.test;

import com.seal_de.domain.PaperItem;
import com.seal_de.service.PaperItemService;
import com.seal_de.test.testConfig.RepositoryTestConfig;
import com.seal_de.test.testConfig.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sealde on 5/16/17.
 */
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RepositoryTestConfig.class, RootConfig.class})
public class PaperItemServiceTest {
    @Autowired
    private PaperItemService paperItemService;

    @Test
    public void reduceChildIndex() {
        List<PaperItem> paperItems = paperItemService.findByPaperDetailId("ff8081815c06b949015c06be4e870005");
        paperItemService.reduceChildIndex(paperItems, 4);
    }
}
