package com.seal_de.service.impl;

import com.seal_de.data.PaperItemRepository;
import com.seal_de.domain.PaperItem;
import com.seal_de.service.PaperItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sealde on 5/5/17.
 */
@Service
public class PaperItemServiceImpl extends AbstractServiceImpl<PaperItemRepository, PaperItem>
        implements PaperItemService {
    @Autowired
    public PaperItemServiceImpl(PaperItemRepository paperItemRepository) {
        this.repository = paperItemRepository;
    }

    public PaperItem getByPaperDetailIdAndChildIndex(String paperDetailId, String childIndex) {
        return repository.getByPaperDetailIdAndChildIndex(paperDetailId, childIndex);
    }
}
