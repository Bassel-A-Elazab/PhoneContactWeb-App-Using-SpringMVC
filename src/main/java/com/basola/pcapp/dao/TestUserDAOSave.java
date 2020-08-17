package com.basola.pcapp.dao;

import com.basola.pcapp.config.SpringRootConfig;
import com.basola.pcapp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAOSave {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        User u = new User();
        u.setName("Usama");
        u.setPhone("0123652314");
        u.setEmail("usama.serag@gmail.com");
        u.setAddress("alex");
        u.setLoginName("usama");
        u.setPassword("456789");
        u.setRole(1);               //Admin Role 
        u.setLoginStatus(1);         //Active
        userDAO.save(u);
        System.out.println("--------Data Saved------");
    }

}
