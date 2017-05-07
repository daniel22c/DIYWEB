package com.daniel22c.DIYWEB.service;

import com.daniel22c.DIYWEB.dao.CategoryDao;
import com.daniel22c.DIYWEB.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Myungho on 4/24/2017.
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Iterable<Category> findAll(){
        return categoryDao.findAll();
    }

    @Override
    public Category findOne(Long id) {
        return categoryDao.findOne(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }
    @Override
    public Category findByName(String name){
        return categoryDao.findByName(name);
    }
}
