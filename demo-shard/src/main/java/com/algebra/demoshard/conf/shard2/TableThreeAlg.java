package com.algebra.demoshard.conf.shard2;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * @author al
 * @date 2020/2/17 17:53
 * @description 分表算法参数 -3
 */
@Slf4j
public class TableThreeAlg implements PreciseShardingAlgorithm<String>  {
    @Override
    public String doSharding(Collection<String> names, PreciseShardingValue<String> value) {
        log.debug("分表算法参数:{},{}",names,value);
        int hash = HashUtil.rsHash(String.valueOf(value.getValue()));
        log.info("hash = {}",hash);
        return "t_user_" + (hash % 2);
    }
}
