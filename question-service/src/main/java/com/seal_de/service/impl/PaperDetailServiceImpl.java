package com.seal_de.service.impl;

import com.seal_de.data.PaperDetailRepository;
import com.seal_de.domain.PaperDetail;
import com.seal_de.service.PaperDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sealde on 5/3/17.
 */
@Service
public class PaperDetailServiceImpl extends AbstractServiceImpl<PaperDetailRepository, PaperDetail> implements PaperDetailService {
    @Autowired
    public PaperDetailServiceImpl(PaperDetailRepository paperDetailRepository) {
        this.repository = paperDetailRepository;
    }

    @Transactional
    public List<PaperDetail> findByPaperId(String paperId) {
        return repository.findByPaperId(paperId);
    }

    @Transactional
    public PaperDetail getByPaperIdAndParentId(String paperId, String parentIndex) {
        return repository.getByPaperIdAndParentId(paperId, parentIndex);
    }
}
