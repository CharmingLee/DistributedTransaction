package com.ming;

public interface OrderService {
    Boolean createOrder(Long userId, Long good_id, int goodCount);
}
