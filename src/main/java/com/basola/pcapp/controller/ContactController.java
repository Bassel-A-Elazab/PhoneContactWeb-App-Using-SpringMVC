
package com.basola.pcapp.controller;

import com.basola.pcapp.domain.Contact;
import com.basola.pcapp.service.ContactService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @RequestMapping(value="/user/contact_form")
    public String contactForm(Model m){
        Contact contact = new Contact();
        m.addAttribute("command",contact);
        return "contact_form";
    }
    
    @RequestMapping(value="/user/savve_contact")
    public String saveContact(@ModelAttribute("command") Contact c, Model m, HttpSession session){
        try{
            Integer userId = (Integer) session.getAttribute("userId");
            c.setUserID(userId);    //FK
            contactService.save(c);
            return "redirect:clist?act=sv";
        }catch(Exception ex){
            ex.printStackTrace();
            m.addAttribute("err","Failed To Save Contact");
            return "contact_form";
        }
    }
}
