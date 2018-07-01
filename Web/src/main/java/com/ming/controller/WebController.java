package com.ming.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ming.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Reference
    private OrderService orderService;
    @Reference
    private UserService userService;
    @Reference
    private GoodService goodService;


    @GetMapping("/")
    public String index(){
        return "hello";
    }

    @GetMapping("/pay")
    public String pay(){
        User user = userService.getUser();
        Good good = goodService.getGood(1L);

        return orderService.createOrder(user.getuId(), good.getgId(), 1).toString();
    }

}
