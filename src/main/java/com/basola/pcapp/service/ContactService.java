
package com.basola.pcapp.service;

import com.basola.pcapp.domain.Contact;
import java.util.List;


public interface ContactService {
    
    public void save(Contact c);
    
    public void update(Contact c);
    
    public void delete(Integer contactId);
    
    public void delete(Integer[] contactId);
    
    public Contact findById(Integer contactId);
    
    public List<Contact> findUserContact(Integer userId);
    
    public List<Contact> findUserContact(Integer userId, String txt);
    
}
