
package com.basola.pcapp.test;

import com.basola.pcapp.config.SpringRootConfig;
import com.basola.pcapp.dao.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDelete {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO=ctx.getBean(UserDAO.class);
        userDAO.delete(4);
        System.out.println("--------Data Deleted------");
        
    }
    
}
