//package com.algebra.demodatasources.conf.db;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.transaction.ChainedTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//
//import javax.annotation.Resource;
//
///**
// * @author al
// */
//@Configuration
//@EnableTransactionManagement
//@ConditionalOnBean({MasterDataSourceConfig.class,SlaveDataSourceConfig.class})
//public class DataSourcesAutoConfiguration implements TransactionManagementConfigurer {
//
//    @Autowired
//    private MasterDataSourceConfig masterDataSourceConfig;
//
//    @Autowired
//    private SlaveDataSourceConfig slaveDataSourceConfig;
//
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//
//        return new ChainedTransactionManager(
//                masterDataSourceConfig.masterTransactionManager(masterDataSourceConfig.masterDruidDataSource()),
//                slaveDataSourceConfig.slaveTransactionManager(slaveDataSourceConfig.slaveDruidDataSource()));
//    }
//}