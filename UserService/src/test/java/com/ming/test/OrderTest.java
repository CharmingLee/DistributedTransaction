package com.ming.test;

import com.ming.dao.UserDAO;
import com.ming.model.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void test(){
        UserDTO dto = new UserDTO();
        dto.setAmount(100000L);
        dto.setUserName("zhangsan");

        int inset = userDAO.insert(dto);

        System.out.println("插入:" + inset);
    }

}
