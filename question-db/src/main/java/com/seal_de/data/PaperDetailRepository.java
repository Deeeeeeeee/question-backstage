package com.seal_de.data;

import com.seal_de.domain.PaperDetail;

import java.util.List;

/**
 * Created by sealde on 5/3/17.
 */
public interface PaperDetailRepository extends IRepository<PaperDetail> {
    List<PaperDetail> findByPaperId(String paperId);
    PaperDetail getByPaperIdAndParentId(String paperId, String parentIndex);
}
