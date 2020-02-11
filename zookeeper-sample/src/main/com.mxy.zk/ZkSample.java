package com.mxy.zk;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.util.List;

@Slf4j
public class ZkSample {

    private static CuratorFramework client = getClient();
    private static Stat stat;
    private static InterProcessMultiLock interProcessMultiLock = null;

    public static CuratorFramework getClient() {
        //sleep 3秒 重试 3次
        return CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(99999999)    // 连接超时时间
                .connectionTimeoutMs(1000) // 会话超时时间
                //刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
    }

    public static void root(String path) throws Exception {
        client.start();
        // 判断是否存在，Stat就是对znode所有属性的一个映射，stat=null表示节点不存在
        stat = client.checkExists().forPath(path);
        if (stat != null) {
            detele("/com");
            log.info("delete this node  {} ", path);
        }

    }

    public static void create(String path, String data) throws Exception {
        // 创建节点
        client.create().creatingParentsIfNeeded() // 若创建节点的父节点不存在则先创建父节点再创建子节点
                .withMode(CreateMode.PERSISTENT) // 创建的是持久节点
                .withACL(Ids.OPEN_ACL_UNSAFE) // 默认匿名权限,权限scheme id:'world,'anyone,:cdrwa
                .forPath(path, data.getBytes());

    }

    public static void detele(String path) throws Exception {
        // 删除节点
        client.delete()
                .guaranteed() // 保障机制，若未删除成功，只要会话有效会在后台一直尝试删除
                .deletingChildrenIfNeeded() // 若当前节点包含子节点，子节点也删除
                .withVersion(stat.getCversion())//子节点版本号
                .forPath(path);
    }

    public static void query(String path) throws Exception {
        // 读取节点数据
        Stat stat = new Stat(); // Stat就是对znode所有属性的一个映射，stat=null表示节点不存在
        log.info(new String(client.getData()
                .storingStatIn(stat) // 在获取节点内容的同时把状态信息存入Stat对象，如果不写的话只会读取节点数据
                .forPath(path)));

    }

    public static void update(String path, String data) throws Exception {
        // 更新节点数据
        client.setData()
                .withVersion(stat.getCversion()) // 乐观锁
                .forPath(path, data.getBytes());

    }

    @SneakyThrows
    public static void lock(String path,CuratorFramework client) {
        interProcessMultiLock = new InterProcessMultiLock(client, Lists.newArrayList(path));
        interProcessMultiLock.acquire();
    }

    @SneakyThrows
    public static void unlock() {
        interProcessMultiLock.release();
    }

    public static void watch(String path) throws Exception {
        // 监听父节点以下所有的子节点, 当子节点发生变化的时候(增删改)都会监听到
        // 为子节点添加watcher事件
        // PathChildrenCache监听数据节点的增删改
        final PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
        // NORMAL:异步初始化, BUILD_INITIAL_CACHE:同步初始化, POST_INITIALIZED_EVENT:异步初始化,初始化之后会触发事件
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        List<ChildData> childDataList = childrenCache.getCurrentData(); // 当前数据节点的子节点数据列表
        log.info("childDataList = {} ", childDataList);
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                if (event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {
                    log.info("------->监控：子节点初始化ok..");
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {
                    log.info("------->监控：添加子节点, path:{}, data:{}", event.getData().getPath(), event.getData().getData());
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                    log.info("------->监控：删除子节点, path:{}", event.getData().getPath());
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                    log.info("------->监控：修改子节点, path:{}, data:{}", event.getData().getPath(), event.getData().getData());
                }
            }
        });
    }


    public static void main(String[] args) {
        String path = "/com";
        String pathAndNode = "/com/mxy/zk/sample";
        try {
            //watch
            watch(path);

            root(pathAndNode);
            //1.create
            create(pathAndNode, "mxy_create_node");
            Thread.sleep(2000);
            //2.query
            query(pathAndNode);
            //3.update
            update(pathAndNode, "mxy_update_node");
            Thread.sleep(1000);
            //4.del
            detele(path);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }


    }

}
