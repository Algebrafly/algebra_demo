package com.algebra.demoshard.conf.shard2;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * @author al
 * @date 2020/2/17 17:50
 * @description 分库算法
 */
@Slf4j
public class DataSourceAlg implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> names, PreciseShardingValue<String> value) {
        log.info("分库算法参数：{}，{}", names, value);
        int hash = HashUtil.rsHash(String.valueOf(value.getValue()));
        return "ds_" + ((hash % 2) + 2) ;
    }
}
