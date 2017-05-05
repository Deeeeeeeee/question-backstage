package com.seal_de.service;

import com.seal_de.domain.PaperItem;

/**
 * Created by sealde on 5/5/17.
 */
public interface PaperItemService extends IService<PaperItem> {
    PaperItem getByPaperDetailIdAndChildIndex(String paperDetailId, String childIndex);
}
