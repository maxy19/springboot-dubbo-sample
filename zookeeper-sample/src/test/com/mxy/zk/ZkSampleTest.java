package com.mxy.zk;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class ZkSampleTest {


    @SneakyThrows
    @Test
    public void lock() {
        CuratorFramework client = ZkSample.getClient();
        String path = "/mxy/zk/lock1";
        client.start();
        new Thread(() -> {
            try {
                ZkSample.lock(path, client);
                System.out.println(Thread.currentThread().getName()+"：开始处理！");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName()+"：结束处理！");
            } catch (Exception e) {

            } finally {
                ZkSample.unlock();
                CloseableUtils.closeQuietly(client);
            }
        }).start();

        new Thread(() -> {
            try {
                ZkSample.lock(path, client);
                System.out.println(Thread.currentThread().getName()+"：开始处理！");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName()+"：结束处理！");
                ZkSample.unlock();
            } catch (Exception e) {

            } finally {
                ZkSample.unlock();
                CloseableUtils.closeQuietly(client);
            }
        }).start();

        System.in.read();

    }

}
