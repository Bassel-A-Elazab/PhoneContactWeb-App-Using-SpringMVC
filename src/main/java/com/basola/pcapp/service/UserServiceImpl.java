
package com.basola.pcapp.service;

import com.basola.pcapp.dao.BaseDAO;
import com.basola.pcapp.dao.UserDAO;
import com.basola.pcapp.domain.User;
import com.basola.pcapp.exception.UserBlockedException;
import com.basola.pcapp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseDAO implements UserService{

    @Autowired
    private UserDAO userDAO;
    
    @Override
    public void register(User u) {
        
        userDAO.save(u);
        
    }

    @Override
    public User login(String loginName, String password) throws UserBlockedException {
        String sql = "SELECT userID, name, phone,email, address, role, loginStatus,loginName"
                +" FROM user WHERE loginName=:ln AND password=:pw";
        
        Map m = new HashMap();
        m.put("ln",loginName);
        m.put("pw",password);
        try{
            User u = getNamedParameterJdbcTemplate().queryForObject(sql, m, new UserRowMapper());
            if(u.getLoginStatus().equals(UserService.LOGIN_STATUS_BLOCKED)){
                throw new UserBlockedException("Your account has been blocked. Contact to admin");
            }else{
                return u;
            }
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<User> getuserList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeLoginStatus(Integer userId, Integer loginStatus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isUserNameExist(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
