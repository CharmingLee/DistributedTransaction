package com.ming.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.ming.*;
import com.ming.dao.OrderDAO;
import com.ming.model.OrderDTO;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 6000)
public class OrderServiceImpl implements OrderService {
    @Reference
    private UserService userService;
    @Reference
    private GoodService goodService;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private CuratorFramework curatorFramework;

    @Override
    public Boolean createOrder(Long userId, Long goodId, int goodCount) throws Exception {
        final boolean[] isSuccess = {true};
        String rootPath = "/test";
        String child = "/Transactional";

        //监听root节点的变化
        PathChildrenCache pathCache = new PathChildrenCache(curatorFramework, rootPath, false);
        pathCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                //有节点移除，所有事务回滚
                if (pathChildrenCacheEvent.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)){
                    isSuccess[0] = false;
                }

                if (pathChildrenCacheEvent.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)){
                    System.out.println("添加节点：" + curatorFramework.getState().name());
                }
            }
        });

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

        //注册本函数到zk
        String path = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(rootPath + child,
                Boolean.TRUE.toString().getBytes());

        //检查是否所有准备完毕
        boolean allSuccess = inset > 0 && decrease && pay;

        if (allSuccess){
            List<String> pathList = curatorFramework.getChildren().forPath(rootPath);
            for (String childPath : pathList) {
                byte[] bytes = curatorFramework.getData().forPath(childPath);
                Boolean childSuccess = Boolean.valueOf(new String(bytes));

                if (!childSuccess){
                    allSuccess = false;
                    break;
                }
            }
        }


        if (allSuccess){
            //TODO 提交

        } else {
            //TODO 回滚
        }

        //清除所有节点
        curatorFramework.delete().forPath(rootPath);

        return allSuccess;
    }
}
