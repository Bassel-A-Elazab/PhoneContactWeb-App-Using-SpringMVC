package com.basola.pcapp.service;

import com.basola.pcapp.dao.BaseDAO;
import com.basola.pcapp.dao.ContactDAO;
import com.basola.pcapp.domain.Contact;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends BaseDAO implements ContactService {
    
    @Autowired
    private ContactDAO contactDAO;
    
    @Override
    public void save(Contact c) {
        contactDAO.save(c);
    }
    
    @Override
    public void update(Contact c) {
        contactDAO.update(c);
    }
    
    @Override
    public void delete(Integer contactId) {
        contactDAO.delete(contactId);
    }
    
    @Override
    public void delete(Integer[] contactId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Contact findById(Integer contactId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Contact> findUserContact(Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Contact> findUserContact(Integer userId, String txt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
