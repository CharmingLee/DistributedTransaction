package com.ming.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.ming.*;
import com.ming.dao.OrderDAO;
import com.ming.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;

@Service(timeout = 6000)
public class OrderServiceImpl implements OrderService {
    @Reference
    private UserService userService;
    @Reference
    private GoodService goodService;
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Boolean createOrder(Long userId, Long goodId, int goodCount) {
        User user = userService.getUser();
        Good good = goodService.getGood(goodId);

        OrderDTO order = new OrderDTO();
        order.setGootAmount(good.getAmount());
        order.setGoodCount(goodCount);
        order.setuId(userId);
        order.setgId(goodId);

        Long inset = orderDAO.inset(order);
        Boolean decrease = goodService.decrease(goodId, goodCount);
        Boolean pay = userService.pay(user.getuId(), good.getAmount() * goodCount);

        return inset > 0 && decrease && pay;
    }
}
