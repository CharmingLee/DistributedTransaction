package com.ming.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ming.User;
import com.ming.UserService;
import com.ming.dao.UserDAO;
import com.ming.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Boolean pay(Long userId, Long amount) {
        return userDAO.pay(userId, amount) > 0;
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
