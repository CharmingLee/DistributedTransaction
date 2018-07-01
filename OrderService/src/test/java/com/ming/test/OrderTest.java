package com.ming.test;

import com.ming.dao.OrderDAO;
import com.ming.model.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderTest {
    @Autowired
    private OrderDAO orderDAO;

    @Test
    public void test(){
        OrderDTO dto = new OrderDTO();
        dto.setgId(1L);
        dto.setGoodCount(1);
        dto.setGootAmount(100L);
        dto.setuId(1L);

        Long inset = orderDAO.inset(dto);

        System.out.println("插入:" + inset);
    }

}
