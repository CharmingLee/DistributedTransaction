package com.ming.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ming.Good;
import com.ming.GoodService;
import com.ming.dao.GoodDAO;
import com.ming.model.GoodDTO;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodDAO goodDAO;

    @Override
    public Boolean decrease(Long goodId, int count) {
        return goodDAO.decrease(goodId, count) > 0;
    }

    @Override
    public Good getGood(Long goodId) {
        GoodDTO goodDTO = goodDAO.getGood(goodId);

        if (goodDTO != null){
            Good g = new Good();
            g.setgId(goodDTO.getgId());
            g.setGoodName(goodDTO.getGoodName());
            g.setGoodCount(goodDTO.getGoodCount());
            g.setAmount(goodDTO.getAmount());

            return g;
        }

        return null;
    }
}
