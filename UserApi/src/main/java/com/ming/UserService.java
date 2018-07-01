package com.ming;

public interface UserService {

    Boolean pay(Long userId, Long amount);

    User getUser();

}
