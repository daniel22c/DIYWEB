package com.daniel22c.DIYWEB.dao;

import com.daniel22c.DIYWEB.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Myungho on 3/13/2017.
 */
@Repository
public interface UserDao extends CrudRepository<User,Long>{
    User findByUsername(String username);
//    @Query("select completedTasks from user where id=:#{principal.id}") // where u.id=:#{principal.id}
//    List<Long> getCompletedTaskIds();
}
