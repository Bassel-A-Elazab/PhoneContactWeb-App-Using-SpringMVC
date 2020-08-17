
package com.basola.pcapp.rm;


import com.basola.pcapp.domain.Contact;
import com.basola.pcapp.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class ContactRowMapper implements RowMapper<Contact> {
    
        @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Contact c=new Contact();
        c.setContactID(rs.getInt("contactId"));
        c.setUserID(rs.getInt("userId"));
        c.setName(rs.getString("name"));
        c.setEmail(rs.getString("email"));
        c.setAddress(rs.getString("address"));       
        c.setPhone(rs.getString("phone"));       
        c.setRemark(rs.getString("remark"));          
        
        return c;
        
    }
    
}
