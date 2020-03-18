package com.mengfei.admApijava.model.retruns;

public class PageInfo {

    private int totalPage;

    private long size;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public boolean isLastPagee() {
        return lastPagee;
    }

    public void setLastPagee(boolean lastPagee) {
        this.lastPagee = lastPagee;
    }

    public boolean isFristPage() {
        return fristPage;
    }

    public void setFristPage(boolean fristPage) {
        this.fristPage = fristPage;
    }

    private boolean lastPagee;

    private boolean fristPage;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
