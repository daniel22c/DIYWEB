package com.daniel22c.DIYWEB.controller;

import com.daniel22c.DIYWEB.model.Category;
import com.daniel22c.DIYWEB.model.DIY;
import com.daniel22c.DIYWEB.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Myungho on 4/24/2017.
 */
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path="/category",method = RequestMethod.GET)
    public String categoryList(Model model){
        Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("newCategory",new Category());
        return "category";
    }

    @RequestMapping(path="/category/{name}", method =RequestMethod.GET)
    public String getDIYListForCategory(@PathVariable String name,Model model){
        Category category = categoryService.findByName(name);
        Iterable<DIY> diys = category.getDiys();
        Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("diys",diys);
        model.addAttribute("categoryName",name);
        return "category-details";
    }

    @RequestMapping(path="/category/add",method= RequestMethod.POST)
    public String addCategory(@ModelAttribute Category category){
        Category tempCategory = categoryService.findByName(category.getName());
        if(tempCategory==null){
            categoryService.save(category);
        }
        return "redirect:/category";
    }
}
