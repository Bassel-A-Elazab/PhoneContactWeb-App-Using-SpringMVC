package com.basola.pcapp.service;

import com.basola.pcapp.config.SpringRootConfig;
import com.basola.pcapp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserService {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserService user = ctx.getBean(UserService.class);

        User u = new User();
        u.setName("tamer");
        u.setPhone("01023652365");
        u.setEmail("tamer.ali@gmail.com");
        u.setAddress("benha");
        u.setLoginName("tamer");
        u.setPassword("456789");
        u.setRole(UserService.ROLE_ADMIN);
        u.setLoginStatus(UserService.LOGIN_SATUS_ACTIVE);
        user.register(u);
        System.out.println("--------User Registered Successfully------");
    }

}
