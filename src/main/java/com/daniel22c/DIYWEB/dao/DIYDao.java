package com.daniel22c.DIYWEB.dao;

import com.daniel22c.DIYWEB.model.DIY;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Myungho on 4/14/2017.
 */
@Repository
public interface DIYDao extends PagingAndSortingRepository<DIY, Long> {
    @Query("select d from DIY d")
    List<DIY> findAll();

    @Query("select d from DIY d where d.title=:title")
    DIY findDIYByTitle(@Param("title") String title);

//    @Query("select d from DIY d where d.id=:id")
//    DIY findDIYById(@Param("id") Long id);
}
