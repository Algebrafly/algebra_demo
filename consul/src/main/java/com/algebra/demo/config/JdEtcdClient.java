package com.algebra.demo.config;

import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.google.protobuf.ByteString;

import com.ibm.etcd.api.KeyValue;
import com.ibm.etcd.api.LeaseGrantResponse;
import com.ibm.etcd.api.RangeResponse;
import com.ibm.etcd.client.KvStoreClient;
import com.ibm.etcd.client.kv.KvClient;
import com.ibm.etcd.client.lease.LeaseClient;
import com.ibm.etcd.client.lease.PersistentLease;
import com.ibm.etcd.client.lock.LockClient;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author al
 * @date 2022/1/5 17:16
 * @description
 */
public class JdEtcdClient {

    private KvClient kvClient;
    private LeaseClient leaseClient;
    private LockClient lockClient;

    public JdEtcdClient(KvStoreClient kvStoreClient) {
        this.kvClient = kvStoreClient.getKvClient();
        this.leaseClient = kvStoreClient.getLeaseClient();
        this.lockClient = kvStoreClient.getLockClient();
    }

    
    public void put(String key, String value) {
        kvClient.put(ByteString.copyFromUtf8(key), ByteString.copyFromUtf8(value)).sync();
    }

    
    public void put(String key, String value, long leaseId) {
        kvClient.put(ByteString.copyFromUtf8(key), ByteString.copyFromUtf8(value), leaseId).sync();
    }

    
    public void putAndGrant(String key, String value, long ttl) {
        LeaseGrantResponse lease = leaseClient.grant(ttl).sync();
        put(key, value, lease.getID());
    }

    
    public String get(String key) {
        RangeResponse rangeResponse = kvClient.get(ByteString.copyFromUtf8(key)).sync();
        List<KeyValue> keyValues = rangeResponse.getKvsList();
        if (CollectionUtils.isEmpty(keyValues)) {
            return null;
        }
        return keyValues.get(0).getValue().toStringUtf8();
    }

    
    public List<KeyValue> getPrefix(String key) {
        RangeResponse rangeResponse = kvClient.get(ByteString.copyFromUtf8(key)).asPrefix().sync();
        return rangeResponse.getKvsList();
    }

    
    public KvClient.WatchIterator watch(String key) {
        return kvClient.watch(ByteString.copyFromUtf8(key)).start();
    }

    
    public KvClient.WatchIterator watchPrefix(String key) {
        return kvClient.watch(ByteString.copyFromUtf8(key)).asPrefix().start();
    }

    
    public long keepAlive(String key, String value, int frequencySecs, int minTtl) throws Exception {
        //minTtl秒租期，每frequencySecs秒续约一下
        PersistentLease lease = leaseClient.maintain().leaseId(SystemClock.now()).keepAliveFreq(frequencySecs).minTtl(minTtl).start();
        long newId = lease.get(3L, TimeUnit.SECONDS);
        put(key, value, newId);
        return newId;
    }

    
    public long timeToLive(long leaseId) {
        try {
            return leaseClient.ttl(leaseId).get().getTTL();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0L;
        }
    }

}
