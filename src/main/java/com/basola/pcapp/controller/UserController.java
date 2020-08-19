package com.basola.pcapp.controller;

import com.basola.pcapp.command.LoginCommand;
import com.basola.pcapp.domain.User;
import com.basola.pcapp.exception.UserBlockedException;
import com.basola.pcapp.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model m) {
        m.addAttribute("command", new LoginCommand());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model m) {
        try {
            User loggedInUser = userService.login(cmd.getLoginName(), cmd.getPassword());
            if (loggedInUser == null) {
                //Failed
                m.addAttribute("err", "Login Failed, Please enter correct user name and password");
                return "index";
            } else {
                if (loggedInUser.getRole().equals(UserService.ROLE_ADMIN)) {
                    return "redirect:admin/dashboard";
                } else if (loggedInUser.getRole().equals(UserService.ROLE_USER)) {
                    return "redirect:user/dashboard";
                } else {
                    m.addAttribute("err", "Invalid User Role");
                    return "index";
                }
            }
        } catch (UserBlockedException ex) {
            m.addAttribute("err", ex.getMessage());
            return "index";
        }

    }

    @RequestMapping(value = "/user/dashboard")
    public String userDashboard() {
        return "dashboard_user";
    }

    @RequestMapping(value = "/admin/dashboard")
    public String adminDashboard() {
        return "dashboard_admin";
    }

}
