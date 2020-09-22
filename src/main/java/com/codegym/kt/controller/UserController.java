package com.codegym.kt.controller;

import com.codegym.kt.Service.Category.CategoryService;
import com.codegym.kt.Service.User.UserService;
import com.codegym.kt.model.Category;
import com.codegym.kt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

   @Autowired
    private UserService userService;

   @Autowired
   private CategoryService categoryService;

   @ModelAttribute("categorys")
    public Iterable<Category> categories(){
        return categoryService.findAll();
   }

    @GetMapping("/user")
    public ModelAndView listUser(){
        Iterable<User> users = userService.findAll();
        ModelAndView modelAndView = new ModelAndView("user/list");
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView saveProvince(@ModelAttribute("user") User user){
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("user/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("message","New user created successfully");
        return  modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null){
            ModelAndView modelAndView = new ModelAndView("user/edit");
            modelAndView.addObject("user", user);
            return  modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-user")
    public ModelAndView updateProvince(@ModelAttribute("user") User user){
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("user/edit");
        modelAndView.addObject("user",user);
        modelAndView.addObject("message","user update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null){
            ModelAndView modelAndView = new ModelAndView("user/delete");
            modelAndView.addObject("user", user);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-user")
    public String deleteProvince(@ModelAttribute("user") User user){
        userService.remove(user.getUserId());
        return "redirect: user";
    }

}
