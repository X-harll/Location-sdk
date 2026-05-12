package com.tecvinson.sdk.models;

import java.util.List;

public class PageResponse<T> {

    private List<T> content;
    private PageMetadata page;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public PageMetadata getPage() {
        return page;
    }

    public void setPage(PageMetadata page) {
        this.page = page;
    }
}