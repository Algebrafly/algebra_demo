//package com.algebra.authentication.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author al
// * @date 2021/6/28 17:53
// * @description
// */
////@Configuration
//public class JedisClusterConfig {
//
////    @Value("${spring.redis.cluster.nodes}")
//    private String redisNode;
//
////    @Value("${spring.redis.password}")
//    private String password;
//
////    @Bean
//    public RedissonClient getRedisson() {
//        String[] nodes = redisNode.split(",");
//        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
//        for (int i = 0; i < nodes.length; i++) {
//            nodes[i] = "redis://" + nodes[i];
//        }
//        RedissonClient redisson = null;
//        Config config = new Config();
//        if (password == null || "".equals(password)) {
//            config.useClusterServers()
//                    .setScanInterval(2000)
//                    .addNodeAddress(nodes);
//        } else {
//            config.useClusterServers()
//                    .setScanInterval(2000)
//                    .addNodeAddress(nodes)
//                    .setPassword(password);
//        }
//        redisson = Redisson.create(config);
//        return redisson;
//    }
//
//    @Bean
//    public JedisCluster getJedisCluster() {
//        String[] redisnodes = redisNode.split(",");
//        Set<HostAndPort> nodes = new HashSet<>();
//        for (String node : redisnodes) {
//            String[] arr = node.split(":");
//            HostAndPort hostAndPort = new HostAndPort(arr[0], Integer.parseInt(arr[1]));
//            nodes.add(hostAndPort);
//        }
//        if (password == null || "".equals(password)) {
//            return new JedisCluster(nodes);
//        } else {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxTotal(500);
//            config.setMinIdle(2);
//            config.setMaxIdle(500);
//            config.setMaxWaitMillis(10000);
//            config.setTestOnBorrow(true);
//            config.setTestOnReturn(true);
//            return new JedisCluster(nodes, 1000, 1000, 100, password, config);
//        }
//    }
//}
