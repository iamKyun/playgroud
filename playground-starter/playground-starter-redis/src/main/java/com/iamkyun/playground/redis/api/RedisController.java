package com.iamkyun.playground.redis.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/status")
    public List<Map<String, Object>> getStatus() {
        List<Map<String, Object>> masterList = new ArrayList<>();
        RedisSentinelConnection sentinelConnection = redisConnectionFactory.getSentinelConnection();
        Collection<RedisServer> masters = sentinelConnection.masters();
        for (RedisServer master : masters) {
            Map<String, Object> masterMap = new LinkedHashMap<>();
            masterMap.put("name", master.getName());
            masterMap.put("host", master.getHost());
            masterMap.put("port", master.getPort());
            masterMap.put("numberReplicas", master.getNumberReplicas());
            masterMap.put("lastPingSent", master.getLastPingSent());
            masterMap.put("lastOkPingReply", master.getLastOkPingReply());
            masterMap.put("slaves", new ArrayList<Map<String, Object>>());
            masterList.add(masterMap);
            Collection<RedisServer> slaves = sentinelConnection.slaves(master);
            for (RedisServer slave : slaves) {
                Map<String, Object> slaveMap = new LinkedHashMap<>();
                slaveMap.put("host", slave.getHost());
                slaveMap.put("port", slave.getPort());
                slaveMap.put("lastPingSent", slave.getLastPingSent());
                slaveMap.put("lastOkPingReply", slave.getLastOkPingReply());
                ((List<Map<String, Object>>) masterMap.get("slaves")).add(slaveMap);
            }
        }
        return masterList;
    }

}
