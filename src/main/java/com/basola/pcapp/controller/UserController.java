
package com.basola.pcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    
    @RequestMapping(value={"/","/index"})
    public String index(){
        return "index";
    }
    
     @RequestMapping(value="user/dashboard")
    public String userDashboard(){
        return "dashboard_user";
    }
    
    
}
