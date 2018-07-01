package com.ming;

import java.io.Serializable;

public class Good implements Serializable {
    private Long gId;
    private String goodName;
    private Integer goodCount;
    private Long amount;

    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Good{" +
                "gId=" + gId +
                ", goodName='" + goodName + '\'' +
                ", goodCount=" + goodCount +
                ", amount=" + amount +
                '}';
    }
}
