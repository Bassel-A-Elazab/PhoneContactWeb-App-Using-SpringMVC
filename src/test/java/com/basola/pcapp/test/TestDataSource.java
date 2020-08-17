
package com.basola.pcapp.test;

import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class TestDataSource {

    
    public static void main(String[] args) {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext();
        DataSource ds = ctx.getBean(DataSource.class);
        JdbcTemplate jt = new JdbcTemplate(ds);
        String sql = "INSERT INTO USER ('name', 'phone', 'email', 'address', 'loginName', 'password') VALUES (?,?,?,?,?,?)";
        Object[] param = new Object[]{"Bassel,01094630032","bassel.alazab@gmail.com","cairo","basola","123456"};
        jt.update(sql,param);
        System.out.println("SQL Executed Success...");
        
        
    }
    
}
