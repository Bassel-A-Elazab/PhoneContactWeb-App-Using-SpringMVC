package com.basola.pcapp.test;

import com.basola.pcapp.config.SpringRootConfig;
import com.basola.pcapp.dao.UserDAO;
import com.basola.pcapp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDataSourceUpdate {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        User u = new User();
        
        u.setUserID(5);
        u.setName("Usama Serag");
        u.setPhone("0123652314");
        u.setEmail("usama.serag2015@gmail.com");
        u.setAddress("alex");
        u.setRole(2);               //Admin Role 
        u.setLoginStatus(2);         //Active
        userDAO.update(u);
        
        System.out.println("--------Data Saved------");
    }

}
