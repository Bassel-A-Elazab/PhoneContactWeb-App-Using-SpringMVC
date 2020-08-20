package com.basola.pcapp.controller;

import com.basola.pcapp.domain.Contact;
import com.basola.pcapp.service.ContactService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/user/contact_form")
    public String contactForm(Model m) {
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";
    }

    @RequestMapping(value = "/user/edit_contact")
    public String prepareEditForm(Model m, HttpSession session, @RequestParam("cid") Integer contactId) {
        session.setAttribute("aContactId", contactId);
        Contact c = contactService.findById(contactId);
        m.addAttribute("command", c);
        return "contact_form";
    }

    @RequestMapping(value = "/user/save_contact")
    public String saveOrUpdateContact(@ModelAttribute("command") Contact c, Model m, HttpSession session) {
        Integer contactId = (Integer) session.getAttribute("aContactId");
        if (contactId == null) {
            try {
                Integer userId = (Integer) session.getAttribute("userId");
                c.setUserID(userId);    //FK
                contactService.save(c);
                return "redirect:clist?act=sv";
            } catch (Exception ex) {
                ex.printStackTrace();
                m.addAttribute("err", "Failed To Save Contact");
                return "contact_form";
            }
        }
    }

    @RequestMapping(value = "/user/clist")
    public String contactList(Model m, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId));
        return "clist";
    }

    @RequestMapping(value = "/user/del_contact")
    public String deleteContact(@RequestParam("cid") Integer contactId) {
        contactService.delete(contactId);
        return "redirect:clist?act=del";
    }
}
