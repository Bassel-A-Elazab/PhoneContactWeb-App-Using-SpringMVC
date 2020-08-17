
package com.basola.pcapp.service;

import com.basola.pcapp.domain.User;
import com.basola.pcapp.exception.UserBlockedException;
import java.util.List;


public interface UserService {
    
    public void register(User u);
    
    public User login(String loginName, String password) throws UserBlockedException;
    
    public List<User> getuserList();
    
    public void changeLoginStatus(Integer userId, Integer loginStatus);
    
    
    
}
