package com.mengfei.admApijava.model.retruns;

import java.util.List;

public class PageReturn<T> {


    private List<T> content;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    private PageInfo pageInfo;
}
