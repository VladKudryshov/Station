package com.epam.training.PhoneStation.controller;

import com.epam.training.PhoneStation.model.*;
import com.epam.training.PhoneStation.service.api.SecurityService;
import com.epam.training.PhoneStation.service.api.UserService;
import com.epam.training.PhoneStation.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_ADMIN"))
            return "redirect:/admin";
        else
            return "redirect:/main";
    }



    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request) {
        String login = request.getUserPrincipal().getName();
        ModelAndView model = new ModelAndView();
        User user = userService.getByLogin(login);
        List<Contract> contracts = user.getContracts();
        List<Payment> payments = user.getPayments();

        if(contracts.size()<1){
            Contract contract = new Contract();
            ServiceModel service =new ServiceModel();
            service.setTitle("Empty");
            service.setCost(0);
            contract.setService(service);
            contract.setEndDate(new Date(0));
            contracts.add(contract);
        }

        model.addObject("listContracts", contracts);
        model.addObject("listPayments", payments);
        model.addObject("user", user);
        model.setViewName("main");

        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) {
        String login = request.getUserPrincipal().getName();
        ModelAndView model = new ModelAndView();
        User user = userService.getByLogin(login);

        model.addObject("user", user);
        model.setViewName("admin");

        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }



    @RequestMapping(value = "/access-deinied", method = RequestMethod.GET)
    public String accessDenied(Model model, String error, String logout) {
        return "access-deinied";
    }




}
