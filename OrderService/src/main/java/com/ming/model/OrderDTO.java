package com.ming.model;

public class OrderDTO {
    private Long oId;
    private Long uId;
    private Long gId;
    private Integer goodCount;
    private Long gootAmount;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Long getGootAmount() {
        return gootAmount;
    }

    public void setGootAmount(Long gootAmount) {
        this.gootAmount = gootAmount;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "oId=" + oId +
                ", uId=" + uId +
                ", gId=" + gId +
                ", goodCount=" + goodCount +
                ", gootAmount=" + gootAmount +
                '}';
    }
}
