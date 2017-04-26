package com.epam.training.PhoneStation.controller;


import com.epam.training.PhoneStation.model.User;
import com.epam.training.PhoneStation.service.api.SecurityService;
import com.epam.training.PhoneStation.service.api.UserService;
import com.epam.training.PhoneStation.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ModelAndView users(ModelAndView model) {
        List<User> users = userService.getAllUser();
        model.addObject("listUsers", users);
        model.setViewName("usersApp");

        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        User user = new User();
        model.addObject("user", user);
        model.setViewName("addUser");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute User user) {
        if (user.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee
            userService.addUser(user);
        } else {
            userService.update(user);
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable(value = "id") Long userId) {
        userService.delete(Long.valueOf(userId));
        return new ModelAndView("redirect:/user/");
    }


}
