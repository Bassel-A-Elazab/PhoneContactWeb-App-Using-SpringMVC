package com.basola.pcapp.dao;

import com.basola.pcapp.domain.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {
    
    @Override
    public void save(User u) {
        String sql = "INSERT INTO user(name, phone, email, address, loginName, password, role, loginStatus)"
                + "VALUES(:name, :phone, :email, :address, :loginName, :password, :role, :loginStatus)";
        Map m = new HashMap();
        m.put("name", u.getName());
        m.put("phone", u.getPhone());
        m.put("email", u.getEmail());
        m.put("address", u.getAddress());
        m.put("loginName", u.getLoginName());
        m.put("password", u.getPassword());
        m.put("role", u.getRole());
        m.put("loginStatus", u.getLoginStatus());
        
        KeyHolder kh = new GeneratedKeyHolder();       //for hold id autoincremented by mysql
        SqlParameterSource ps = new MapSqlParameterSource(m);
        super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
        Integer userId = kh.getKey().intValue();
        u.setUserID(userId);
    }
    
    @Override
    public void update(User u) {
        String sql = "UPDATE user SET name = :name,"
                + "phone = :phone, "
                + "email = :email,"
                + "address = :address,"
                + "role = :role,"
                + "loginStatus = :loginStatus"
                + " WHERE userID = :UserId";
        Map m = new HashMap();
        m.put("name", u.getName());
        m.put("phone", u.getPhone());
        m.put("email", u.getEmail());
        m.put("address", u.getAddress());
        m.put("role", u.getRole());
        m.put("loginStatus", u.getLoginStatus());
        m.put("UserId", u.getUserID());
        
        getNamedParameterJdbcTemplate().update(sql, m);
    }
    
    @Override
    public void delete(User u) {
        this.delete(u.getUserID());
    }
    
    @Override
    public void delete(Integer userId) {
        String sql = "DELETE FORM user where userID = ?";
        getJdbcTemplate().update(sql, userId);
    }
    
    @Override
    public User findById(Integer userId) {
        return null;
        
    }
    
    @Override
    public List<User> findAll() {
        return null;
        
    }
    
    @Override
    public List<User> findByProperty(String propName, Object propValue) {
        return null;
        
    }
    
}
