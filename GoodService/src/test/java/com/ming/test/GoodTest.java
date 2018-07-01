package com.ming.test;

import com.ming.dao.GoodDAO;
import com.ming.model.GoodDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoodTest {
    @Autowired
    private GoodDAO goodDAO;

    @Test
    public void test(){
        GoodDTO dto = new GoodDTO();
        dto.setGoodCount(100);
        dto.setGoodName("orange");

        Long insert = goodDAO.insert(dto);

        System.out.println(insert);
    }

    @Test
    public void test2(){
        List<GoodDTO> select = goodDAO.select();

        System.out.println("返回:" + select);
    }

}
