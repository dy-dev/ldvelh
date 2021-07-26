//package com.arcreane;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataConfig {
//
//
//    @Bean
//    public DataSource getDataDource(){
//        DataSourceBuilder dsb = DataSourceBuilder.create();
//        dsb.driverClassName("com.mysql.cj.jdbc.Driver");
//        dsb.username("dom");
//        dsb.password("dom");
//        dsb.url("jdbc:mysql://localhost:3306/ldvelh?useSSL=false&allowPublicKeyRetrieval=true");
//        return dsb.build();
//    }
//}
