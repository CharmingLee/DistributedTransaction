package com.charminglee911.test;

import com.ming.Application;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author CharmingLee
 * @Date 2018/8/16
 * @Description TODO
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class WebTest {
    @Autowired
    private CuratorFramework curatorFramework;

    @Test
    public void test() throws Exception {
        String path1 = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test");
        String path2 = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test");

        System.out.println(path1);
        System.out.println(path2);
    }
}
