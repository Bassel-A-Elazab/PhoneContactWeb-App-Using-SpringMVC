
package com.basola.pcapp.controller;

import com.basola.pcapp.domain.Contact;
import com.basola.pcapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
}
