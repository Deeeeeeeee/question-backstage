package com.seal_de.data;

import com.seal_de.domain.PaperItem;

/**
 * Created by sealde on 5/5/17.
 */
public interface PaperItemRepository extends IRepository<PaperItem> {
    PaperItem getByPaperDetailIdAndChildIndex(String paperDetailId, String childIndex);
}
