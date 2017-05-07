package com.daniel22c.DIYWEB.service;

import com.daniel22c.DIYWEB.model.DIY;

/**
 * Created by Myungho on 4/14/2017.
 */
public interface DIYService {
    Iterable<DIY> findAll();
    DIY findOne(Long id);
    DIY findDIYByTitle(String title);
    void save(DIY diy);
}
