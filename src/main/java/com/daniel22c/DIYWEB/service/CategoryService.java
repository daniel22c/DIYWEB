package com.daniel22c.DIYWEB.service;

import com.daniel22c.DIYWEB.model.Category;

/**
 * Created by Myungho on 4/24/2017.
 */
public interface CategoryService {
    Iterable<Category> findAll();
    Category findOne(Long id);
    void save(Category category);

    Category findByName(String name);
}
