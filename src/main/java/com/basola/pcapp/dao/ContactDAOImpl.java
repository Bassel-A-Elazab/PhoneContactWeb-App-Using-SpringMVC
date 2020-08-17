package com.basola.pcapp.dao;

import com.basola.pcapp.domain.Contact;
import com.basola.pcapp.rm.ContactRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ContactDAOImpl extends BaseDAO implements ContactDAO {

    @Override
    public void save(Contact c) {

        String sql = "INSERT INTO contact(userID, name, phone, email, address, remark) VALUES(:userId, :name, :phone, :email, :address, :remark)";
        Map m = new HashMap();
        m.put("userId", c.getUserID());
        m.put("name", c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remark", c.getRemark());
        SqlParameterSource ps = new MapSqlParameterSource(m);
        KeyHolder kh = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(sql, ps, kh);
        c.setContactID(kh.getKey().intValue());

    }

    @Override
    public void update(Contact c) {

        String sql = "UPDATE contact SET name=:name, phone=:phone, email=:email, address=:address, remark=:remark WHERE contactID=:contactID";
        Map m = new HashMap();
        m.put("contactId", c.getContactID());
        m.put("name", c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remark", c.getRemark());
        getNamedParameterJdbcTemplate().update(sql, m);

    }

    @Override
    public void delete(Contact c) {

        this.delete(c.getContactID());

    }

    @Override
    public void delete(Integer contactId) {

        String sql = "DELETE FROM contact WHERE contactID=?";
        getJdbcTemplate().update(sql, contactId);
    }

    @Override
    public Contact findById(Integer contactId) {

        String sql = "SELECT contactID, userID, name, phone, email, address, remark FROM contact WHERE contactID=?";
        return getJdbcTemplate().queryForObject(sql, new ContactRowMapper(), contactId);

    }

    @Override
    public List<Contact> findAll() {

        String sql = "SELECT contactID, userID, name, phone, email, address, remark FROM contact";
        return getJdbcTemplate().query(sql, new ContactRowMapper());

    }

    @Override
    public List<Contact> findByProperty(String propName, Object propValue) {

        String sql = "SELECT contactID, userID, name, phone, email, address, remark FROM contact WHERE " + propName + "=?";
        return getJdbcTemplate().query(sql, new ContactRowMapper(), propValue);

    }

}
