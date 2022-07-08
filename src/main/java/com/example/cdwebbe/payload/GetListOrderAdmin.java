package com.example.cdwebbe.payload;

import java.util.ArrayList;
import java.util.List;

public class GetListOrderAdmin {
    private List<?> list = new ArrayList<>();
    private int totalPage;

    public GetListOrderAdmin(List<?> list, int totalPage) {
        this.list = list;
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
