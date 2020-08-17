package com.basola.pcapp.service;

import com.basola.pcapp.dao.BaseDAO;
import com.basola.pcapp.dao.ContactDAO;
import com.basola.pcapp.domain.Contact;
import com.basola.pcapp.rm.ContactRowMapper;
import com.basola.pcapp.util.StringUtil;
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
        String ids = StringUtil.toCommaSeparatedString(contactId);
        String sql = "DELETE FROM contact WHERE contactID IN(" + ids + ")";
        getJdbcTemplate().update(sql);
    }

    @Override
    public Contact findById(Integer contactId) {
        return contactDAO.findById(contactId);
    }

    @Override
    public List<Contact> findUserContact(Integer userId) {
        return contactDAO.findByProperty("userId", userId);
    }

    @Override
    public List<Contact> findUserContact(Integer userId, String txt) {
        String sql = "SELECT contactId, userId, name, phone, email, address, remark FROM contact WHERE userId=? AND (name LIKE '%" + txt + "%' OR address LIKE '%" + txt + "%' OR phone LIKE '%" + txt + "%' OR email LIKE '%" + txt + "%' OR remark LIKE '%" + txt + "%')";
        return getJdbcTemplate().query(sql, new ContactRowMapper(), userId);
    }

}
