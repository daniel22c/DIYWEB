package com.daniel22c.DIYWEB.service;

import com.daniel22c.DIYWEB.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Myungho on 3/13/2017.
 */
public interface UserService extends UserDetailsService{
    User findByUsername(String username);
    void save(User user);
    boolean userHasCompletedTask(User user, Long id);
//    List<Long> getCompletedTaskIds();

}
