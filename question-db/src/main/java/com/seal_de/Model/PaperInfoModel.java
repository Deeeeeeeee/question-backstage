package com.seal_de.Model;

import com.seal_de.domain.Paper;
import com.seal_de.domain.PaperDetail;

import java.util.List;

/**
 * Created by sealde on 5/3/17.
 */
public class PaperInfoModel {
    private Paper basicInfo;
    private String paperUrl;
    private List<PaperDetail> detail;

    public PaperInfoModel(Paper paper, String paperUrl, List<PaperDetail> detail) {
        this.basicInfo = paper;
        this.paperUrl = paperUrl;
        this.detail = detail;
    }

    public Paper getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(Paper basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    public List<PaperDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<PaperDetail> detail) {
        this.detail = detail;
    }
}
