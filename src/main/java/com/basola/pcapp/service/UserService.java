
package com.basola.pcapp.service;

import com.basola.pcapp.domain.User;
import com.basola.pcapp.exception.UserBlockedException;


public interface UserService {
    
    public void register(User u);
    
    public User login(String loginName, String password) throws UserBlockedException;
    
    
}
