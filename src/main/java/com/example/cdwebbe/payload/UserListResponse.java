package com.example.cdwebbe.payload;

import com.example.cdwebbe.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserListResponse {
    private int page;
    private int limit;
    private int totalPage;
    private int totalUser;
    private List<UserDTO> userDTOList =new ArrayList<>();

    /**
     * Getter/setter
     * @return
     */

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }
}
