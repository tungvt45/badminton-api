package com.prcbadminton.badminton.dto;

import java.util.List;

public class PageDTO<T> {
    private int maxPage;
    private List<T> data;

    public PageDTO() {
    }

    public int getMaxPage() {
        return maxPage;
    }

    public PageDTO setMaxPage(int maxPage) {
        this.maxPage = maxPage;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public PageDTO setData(List<T> data) {
        this.data = data;
        return this;
    }
}
