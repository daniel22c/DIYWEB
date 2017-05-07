package com.daniel22c.DIYWEB.dao;

import com.daniel22c.DIYWEB.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends CrudRepository<Task, Long> {

    @Query("select t from Task t")
    List<Task> findAll();


}
