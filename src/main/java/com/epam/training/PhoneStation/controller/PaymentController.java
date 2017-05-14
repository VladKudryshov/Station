package com.epam.training.PhoneStation.controller;

import com.epam.training.PhoneStation.entity.PaymentEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.PaymentService;
import com.epam.training.PhoneStation.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    private Authentication auth;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView paymentsAllUser(ModelAndView model) {
        List<PaymentEntity> payments = paymentService.getAll();
        model.addObject("listPayments", payments);
        model.setViewName("admin/paymentsUsers");
        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView paymentsByUser(ModelAndView model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        UserEntity user = userService.getByUserName(userName);
        List<PaymentEntity> payments = user.getPaymentEntities();
        model.addObject("listPayments", payments);
        model.setViewName("users/payments");
        return model;
    }

    @RequestMapping(value = "{id}/pay", method = RequestMethod.GET)
    public String pay(@PathVariable long id) {
        PaymentEntity payment = paymentService.getById(id);
        paymentService.pay(payment);

        return "redirect:/payment";
    }



}
