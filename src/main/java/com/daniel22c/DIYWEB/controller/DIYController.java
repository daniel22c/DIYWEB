package com.daniel22c.DIYWEB.controller;

import com.daniel22c.DIYWEB.model.Category;
import com.daniel22c.DIYWEB.model.DIY;
import com.daniel22c.DIYWEB.model.User;
import com.daniel22c.DIYWEB.service.CategoryService;
import com.daniel22c.DIYWEB.service.DIYService;
import com.daniel22c.DIYWEB.service.UserService;
import com.daniel22c.DIYWEB.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;


/**
 * Created by Myungho on 4/14/2017.
 */
@Controller
public class DIYController {
    @Autowired
    private DIYService diyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping({"/","/DIYWEB","DIY"})
    public String diyList(Model model) {
        Iterable<DIY> diys = diyService.findAll();
        Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("DIYs",diys);
        model.addAttribute("newDIY", new DIY());
        model.addAttribute("categories",categories);
        return "home";
    }
    @RequestMapping(path = "/DIYs", method = RequestMethod.POST)
    public String addDIY(@ModelAttribute DIY diy, Principal principal, RedirectAttributes redirectAttributes) {
        DIY tempDIY = diyService.findDIYByTitle(diy.getTitle());

        if(tempDIY==null) { //save if DIY title is not already in DB
            diyService.save(diy);
            // Flash message
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("New DIY title submitted!", FlashMessage.Status.SUCCESS));
        }
        return "redirect:/";
    }

    //display list of DIY favorites
    @RequestMapping(path ="/favorites", method= RequestMethod.GET)
    public String showDIYFavorites(Model model, Principal principal){
        User loggedUser = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();

        List<DIY> favoriteDIYs = loggedUser.getDiys();
        Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("favDIYs",favoriteDIYs);
        model.addAttribute("categories",categories);
        return "favorites";
    }

    //user has DIY list diys
    //add DIY to user
    @RequestMapping(path="/addFavoriteDIY/{title}", method = RequestMethod.GET)
    public String addDIYFavorites(@PathVariable String title, Principal principal){
        User loggedUser = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();

        DIY diy = diyService.findDIYByTitle(title);
        if(diy!=null) {
            loggedUser.addDIYs(diy);
            userService.save(loggedUser);
        }
        return "redirect:/favorites";
    }
}
