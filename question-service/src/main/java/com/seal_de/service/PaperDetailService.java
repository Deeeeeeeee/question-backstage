package com.seal_de.service;

import com.seal_de.domain.PaperDetail;

import java.util.List;

/**
 * Created by sealde on 5/3/17.
 */
public interface PaperDetailService extends IService<PaperDetail> {
    List<PaperDetail> findByPaperId(String paperId);
    PaperDetail getByPaperIdAndParentId(String paperId, String parentIndex);
}
