package com.ming.test;

import com.ming.dao.UserDAO;
import com.ming.model.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderTest {
    @Autowired
    private UserDAO userDAO;
    @Resource(name="transactionManager")
    private DataSourceTransactionManager transactionManager;
    private ConcurrentHashMap<String, CountDownLatch> latchs = new ConcurrentHashMap<String, CountDownLatch>();
    private ConcurrentHashMap<String, Boolean> successMap = new ConcurrentHashMap<String, Boolean>();

    @Test
    public void test(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态



        //zk注册事务

        //zk注册回调

        //执行数据库操作
        UserDTO dto = new UserDTO();
        dto.setAmount(100000L);
        dto.setUserName("lisi");
        int inset = userDAO.insert(dto);
        System.out.println("插入:" + inset);

        //zk预备提交，更新数据标记已经完成

        //睡眠等待zk回调
        successMap.put("test", false);
        try {
            CountDownLatch latch = new CountDownLatch(1);
            latchs.put("test", latch);
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (successMap.get("test")){
            transactionManager.commit(status);
        } else {
            transactionManager.rollback(status);
        }

    }

}
