package com.myccnice.practice.manual.redisson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.RequestWrapper;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

public class App {

    private static ExecutorService executorService = Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());

    @RequestWrapper
    public static void main( String[] args ) throws Exception {
        // 默认连接地址 127.0.0.1:6379
        // RedissonClient redisson = Redisson.create();

        Config config = new Config();
        SingleServerConfig server = config.useSingleServer();
        server.setAddress("redis://zookeeper.jtongi.cn:7000");
        server.setPassword("44425958db31f2ea");
        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("anyLock");
        if (!lock.tryLock()) {
            return;
        }
        lock.unlock();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (!lock.tryLock()) {
                    return;
                }
                lock.unlock();
            }
        });
        Thread.sleep(1000 * 60);
        System.out.println();
    }
}
