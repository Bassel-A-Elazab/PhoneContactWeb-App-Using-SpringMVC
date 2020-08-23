
package com.basola.pcapp.service;

import com.basola.pcapp.domain.User;
import com.basola.pcapp.exception.UserBlockedException;
import java.util.List;


public interface UserService {
    
    public static final Integer LOGIN_SATUS_ACTIVE = 1;
    public static final Integer LOGIN_STATUS_BLOCKED = 2;
    
    public static final Integer ROLE_ADMIN = 1;
    public static final Integer ROLE_USER = 2;
    
    public void register(User u);
    
    public User login(String loginName, String password) throws UserBlockedException;
    
    public List<User> getuserList();
    
    public void changeLoginStatus(Integer userId, Integer loginStatus);
    
    public Boolean isUserNameExist(String username);
    
    public void delete(Integer userId);
    
    public void delete(Integer[] contactId);
    
}
