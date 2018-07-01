package com.ming;

public interface GoodService {
    Boolean decrease(Long goodId, int count);

    Good getGood(Long goodId);
}
