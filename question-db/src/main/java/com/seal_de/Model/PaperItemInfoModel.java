package com.seal_de.Model;

import com.seal_de.domain.PaperItem;

/**
 * Created by sealde on 5/5/17.
 */
public class PaperItemInfoModel {
    private String parentIndex;
    private PaperItem paperItem;

    public String getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(String parentIndex) {
        this.parentIndex = parentIndex;
    }

    public PaperItem getPaperItem() {
        return paperItem;
    }

    public void setPaperItem(PaperItem paperItem) {
        this.paperItem = paperItem;
    }
}
