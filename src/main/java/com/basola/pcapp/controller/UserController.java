package com.basola.pcapp.controller;

import com.basola.pcapp.command.LoginCommand;
import com.basola.pcapp.command.UserCommand;
import com.basola.pcapp.domain.User;
import com.basola.pcapp.exception.UserBlockedException;
import com.basola.pcapp.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
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
    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model m, HttpSession session) {
        try {
            User loggedInUser = userService.login(cmd.getLoginName(), cmd.getPassword());
            if (loggedInUser == null) {
                //Failed
                m.addAttribute("err", "Login Failed, Please enter correct user name and password");
                return "index";
            } else {
                if (loggedInUser.getRole().equals(UserService.ROLE_ADMIN)) {
                    
                    addUserInSession(loggedInUser, session);
                    return "redirect:admin/dashboard";
                } else if (loggedInUser.getRole().equals(UserService.ROLE_USER)) {
                    addUserInSession(loggedInUser, session);
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

    @RequestMapping(value="logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index?act=log";
    }
    
    @RequestMapping(value = "/reg_form")
    public String registrationForm(Model m){
        UserCommand cmd = new UserCommand();
        m.addAttribute("command",cmd);
        return "reg_form";
    }
    
    @RequestMapping(value="/register")
    public String registerUser(@ModelAttribute("command") UserCommand cmd, Model m){
        User user = cmd.getUser();
        user.setRole(UserService.ROLE_USER);
        user.setLoginStatus(UserService.LOGIN_SATUS_ACTIVE);
        userService.register(user);
        return "redirect:index>act=reg";
    }
    @RequestMapping(value = "/user/dashboard")
    public String userDashboard() {
        return "dashboard_user";
    }
    
    @RequestMapping(value = "/admin/dashboard")
    public String adminDashboard() {
        return "dashboard_admin";
    }

    private void addUserInSession(User u, HttpSession session){
        session.setAttribute("user", u);
        session.setAttribute("userId", u.getUserID());
        session.setAttribute("role", u.getRole());
    }
}
