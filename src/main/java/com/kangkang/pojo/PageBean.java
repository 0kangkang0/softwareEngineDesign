package com.kangkang.pojo;

import lombok.Data;

import java.util.List;
@Data
public class PageBean<T> {
    private Integer total;
    private List<T>rows;

    public PageBean(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
