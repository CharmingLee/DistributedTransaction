package com.ming.model;

public class UserDTO {
    private Long uId;
    private String userName;
    private Long amount;

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
