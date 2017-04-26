package com.epam.training.PhoneStation.controller;


import com.epam.training.PhoneStation.model.ServiceModel;
import com.epam.training.PhoneStation.model.User;
import com.epam.training.PhoneStation.service.api.SecurityService;
import com.epam.training.PhoneStation.service.api.ServiceModelService;
import com.epam.training.PhoneStation.service.api.UserService;
import com.epam.training.PhoneStation.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(value = "/service")
public class ServiceController {
    @Autowired
    private ServiceModelService serviceModelService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView service(ModelAndView model) {
        List<ServiceModel> services = serviceModelService.getAll();
        model.addObject("listService", services);
        model.setViewName("service");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addService(ModelAndView model) {
        User user = new User();
        model.addObject("user", user);
        model.setViewName("addUser");
        return model;
    }

    /*@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveService(@ModelAttribute User user) {
        if (user.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee
            userService.addUser(user);
        } else {
            userService.update(user);
        }
        return new ModelAndView("redirect:/");
    }*/

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteService(@PathVariable(value = "id") Long userId) {
        serviceModelService.delete(Long.valueOf(userId));
        return new ModelAndView("redirect:/user/");
    }


}
