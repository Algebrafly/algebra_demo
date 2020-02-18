package com.algebra.demoshard.conf.shard2;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * @author al
 * @date 2020/2/17 17:53
 * @description 分表算法参数 -1
 */
@Slf4j
public class TableOneAlg implements PreciseShardingAlgorithm<String>  {
    @Override
    public String doSharding(Collection<String> names, PreciseShardingValue<String> value) {
        log.debug("分表算法参数:{},{}",names,value);
        int hash = HashUtil.rsHash(String.valueOf(value.getValue()));
        return "table_one_" + (hash % 5+1);
    }
}
