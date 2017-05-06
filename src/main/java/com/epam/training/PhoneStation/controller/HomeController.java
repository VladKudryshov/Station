package com.epam.training.PhoneStation.controller;

import com.epam.training.PhoneStation.entity.*;
import com.epam.training.PhoneStation.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

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
        UserEntity userEntity = userService.getByLogin(login);
        List<ContractEntity> contractEntities = userEntity.getContractEntities();
        List<PaymentEntity> paymentEntities = userEntity.getPaymentEntities();

        if(contractEntities.size()<1){
            ContractEntity contractEntity = new ContractEntity();
            ServiceEntity service =new ServiceEntity();
            service.setTitleEn("Empty");
            service.setCost(0);
            contractEntity.setService(service);
            contractEntity.setEndDate(new Date(0));
            contractEntities.add(contractEntity);
        }

        model.addObject("listContracts", contractEntities);
        model.addObject("listPayments", paymentEntities);
        model.addObject("user", userEntity);
        model.setViewName("main");

        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) {
        String login = request.getUserPrincipal().getName();
        ModelAndView model = new ModelAndView();
        UserEntity userEntity = userService.getByLogin(login);

        model.addObject("user", userEntity);
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
    public String accessDenied() {
        return "access-deinied";
    }




}
