package com.daniel22c.DIYWEB.service;

import com.daniel22c.DIYWEB.dao.UserDao;
import com.daniel22c.DIYWEB.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Myungho on 3/13/2017.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from the db
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        //return user object
        return user;
    }
    @Override
    public void save(User user){
        userDao.save(user);
    }

    @Override
    public boolean userHasCompletedTask(User user, Long id){
        List<Long> taskIds = user.getCompletedTasks();
        for(Long taskId :taskIds){
            if(taskId==id){
                return true;
            }
        }
        return false;
    }
//    @Override
//    public List<Long> getCompletedTaskIds(){
//        return userDao.getCompletedTaskIds();
//    }


}
