package com.ming.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ming.User;
import com.ming.UserService;
import com.ming.dao.UserDAO;
import com.ming.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Resource(name="transactionManager")
    private DataSourceTransactionManager transactionManager;
    private ConcurrentHashMap<String, CountDownLatch> latchs = new ConcurrentHashMap<String, CountDownLatch>();

    @Override
    public Boolean pay(Long userId, Long amount) {
        //开启事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        //zk注册事务

        int pay = userDAO.pay(userId, amount);

        //zk预备提交，更新数据标记已经完成

        //睡眠等待zk回调
        boolean b = true;
        try {
            CountDownLatch latch = new CountDownLatch(1);
            latchs.put("test", latch);
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            b = false;
        }

        if (b){
            transactionManager.commit(status);
        } else {
            transactionManager.rollback(status);
        }

        return pay > 0 && b;
    }

    @Override
    public User getUser() {
        List<UserDTO> users = userDAO.findAll();

        if (users != null && !users.isEmpty()){
            UserDTO userDTO = userDAO.findAll().get(0);
            User user = new User();
            user.setuId(userDTO.getuId());
            user.setUserName(userDTO.getUserName());
            user.setAmount(userDTO.getAmount());

            return user;
        }

        return null;
    }
}
