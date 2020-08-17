package com.basola.pcapp.test;

import com.basola.pcapp.config.SpringRootConfig;
import com.basola.pcapp.dao.UserDAO;
import com.basola.pcapp.domain.User;
import java.util.List;
import javax.faces.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAOQuery {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        
        // Find Single Record
        User u = userDAO.findById(3);
        System.out.println("User Info : ");
        System.out.println(u.getUserID());
        System.out.println(u.getName());
        System.out.println(u.getPhone());
        System.out.println(u.getEmail());
        System.out.println(u.getAddress());
        System.out.println(u.getLoginName());
        System.out.println(u.getLoginStatus());
        System.out.println(u.getRole());
        
        // Find All Recorded
        
        List<User> u1 = userDAO.findAll();
        for(User user : u1){
            System.out.println("User id : "+user.getUserID() + " Name : "+user.getName());
        }

    }
}
