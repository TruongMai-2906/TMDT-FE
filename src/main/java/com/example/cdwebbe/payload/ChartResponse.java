package com.example.cdwebbe.payload;

import java.util.ArrayList;
import java.util.List;

public class ChartResponse {
    private List<String > labelList = new ArrayList<>();
    private List<Integer> amountOrder= new ArrayList<>();
    private List<Double> netRevenue= new ArrayList<>();
    private String time;

    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public List<Integer> getAmountOrder() {
        return amountOrder;
    }

    public void setAmountOrder(List<Integer> amountOrder) {
        this.amountOrder = amountOrder;
    }

    public List<Double> getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(List<Double> netRevenue) {
        this.netRevenue = netRevenue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
