package com.myccnice.practice.manual.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

public class App {
    public static void main( String[] args ) {
        // 默认连接地址 127.0.0.1:6379
        // RedissonClient redisson = Redisson.create();

        Config config = new Config();
        SingleServerConfig server = config.useSingleServer();
        server.setAddress("redis://zookeeper.jtongi.cn:7000");
        server.setPassword("44425958db31f2ea");
        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("anyLock");
        // 最常见的使用方法
        lock.lock();
    }
}
