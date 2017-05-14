package com.epam.training.PhoneStation.controller;

import com.epam.training.PhoneStation.entity.CallEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.CallService;
import com.epam.training.PhoneStation.service.api.PaymentService;
import com.epam.training.PhoneStation.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(value = "/call")
public class CallController {

    @Autowired
    private UserService userService;

    @Autowired
    private CallService callService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView calls(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        UserEntity user = userService.getByUserName(request.getUserPrincipal().getName());
        List<CallEntity> calls = user.getCallEntities();
        model.addObject("listCalls", calls);
        model.setViewName("users/call");
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addCall(HttpServletRequest request, @RequestBody String timeCall) {

        try {
            String username = request.getUserPrincipal().getName();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            Time time = new Time(sdf.parse(timeCall).getTime());
            CallEntity call = callService.addCall(username, time);
            paymentService.addPayment(username, call);

        } catch (Exception e) {
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
