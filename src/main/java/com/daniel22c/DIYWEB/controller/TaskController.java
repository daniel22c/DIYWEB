package com.daniel22c.DIYWEB.controller;

import com.daniel22c.DIYWEB.model.Category;
import com.daniel22c.DIYWEB.model.DIY;
import com.daniel22c.DIYWEB.model.Task;
import com.daniel22c.DIYWEB.model.User;
import com.daniel22c.DIYWEB.service.CategoryService;
import com.daniel22c.DIYWEB.service.DIYService;
import com.daniel22c.DIYWEB.service.TaskService;
import com.daniel22c.DIYWEB.service.UserService;
import com.daniel22c.DIYWEB.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private DIYService diyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    //Show DIY instructions
    @RequestMapping("/DIY-details/{title}")
    public String showDIYDetails(@PathVariable String title, ModelMap modelMap,Principal principal){
        DIY diy = diyService.findDIYByTitle(title);
        Iterable<Task> tasks = diy.getTasks();

        User loggedUser = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        //List<Long> completedTasks = loggedUser.getCompletedTasks();
        List<Long> completedTasks = loggedUser.getCompletedTasks();
        String name = loggedUser.getUsername();
        System.out.println(name+":"+completedTasks.toString());

        //set task if complete
        if(completedTasks.size()>0) {
            for (Task task:tasks) {
                task.setComplete(false);
                for (Long ctId : completedTasks) {
                    //if task found on user's completed task list set task completed
                    if (task.getId() == ctId) {
                        task.setComplete(true);
                    }
                }
            }
        }

        //sort tasks by id
        List<Long> sorted = new ArrayList();
        for(Task task:tasks){
            sorted.add(task.getId());
        }
        java.util.Collections.sort(sorted);

        //Set<Long> sortedTaskIDs = new TreeSet<Long>(unsorted);
        LinkedHashSet<Task> sortedTasks = new LinkedHashSet();
        for(Long tid:sorted){
            sortedTasks.add(diy.getTaskByTaskID(tid));
        }

        //show DIY's categories

        Iterable<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories",categories);
        modelMap.put("tasks",sortedTasks);
        modelMap.put("diy",diy);
        modelMap.put("newTask", new Task());
        return "DIY-details";
    }

    @RequestMapping(path = "/DIY-details/{title}/mark", method = RequestMethod.POST)
    public String toggleComplete(@PathVariable String title,@RequestParam Long id,Principal principal,RedirectAttributes redirectAttributes) {

//<input type="hidden" name="diy" th:value="${diy}"/>
        User loggedUser = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        Authentication authentication = new UsernamePasswordAuthenticationToken(loggedUser, loggedUser.getPassword(), loggedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //check if user has the task
        loggedUser.addOrRemoveTasks(id);
        // Flash message
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("Task has been toggled!", FlashMessage.Status.SUCCESS));

        userService.save(loggedUser);
        return "redirect:/DIY-details/"+title;
    }

    //add new DIY task
    //DIY has tasks - add task to DIY
    @RequestMapping(path="/DIY-details/{title}", method = RequestMethod.POST)
    public String addTask(@ModelAttribute Task task,@PathVariable String title, Principal principal, RedirectAttributes redirectAttributes) {
        task.setDiy(diyService.findDIYByTitle(title));
        taskService.save(task);
        // Flash message
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("New Task submitted!", FlashMessage.Status.SUCCESS));
        return "redirect:/DIY-details/"+title;
    }
}