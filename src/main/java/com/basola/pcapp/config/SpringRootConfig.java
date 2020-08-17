
package com.basola.pcapp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = {"com.basola"})


public class SpringRootConfig {
    
     //TODO later for services and repository
    
    @Bean
    public BasicDataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/contact_phone");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setMaxTotal(2);
        ds.setInitialSize(1);
        ds.setTestOnBorrow(true);
        //ds.setValidationQuery("SELECT 1");
        ds.setDefaultAutoCommit(true);
        
        return ds;
    }
}
