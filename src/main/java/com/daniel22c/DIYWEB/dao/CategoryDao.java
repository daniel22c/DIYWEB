package com.daniel22c.DIYWEB.dao;

import com.daniel22c.DIYWEB.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Myungho on 4/24/2017.
 */
@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {
    @Query("select c from Category c")
    List<Category> findAll();

    @Query("select c from Category c where c.name=:name")
    Category findByName(@Param("name") String name);
}
