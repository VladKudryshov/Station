package com.epam.training.PhoneStation.controller;


import com.epam.training.PhoneStation.entity.ContractEntity;
import com.epam.training.PhoneStation.entity.PaymentEntity;
import com.epam.training.PhoneStation.entity.Role;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ModelAndView users(ModelAndView model) {
        List<UserEntity> userEntities = userService.getAllUser();
        model.addObject("listUsers", userEntities);
        model.setViewName("admin/usersApp");
        return model;
    }

    @RequestMapping(value = "/unpaid", method = RequestMethod.GET)
    public ModelAndView userWaitBlock() {
        ModelAndView model = new ModelAndView();
        List<UserEntity> usersForBlock = userService.getNotPaidUser();

        model.addObject("userForBlock", usersForBlock);
        model.setViewName("admin/unpaidUsers");

        return model;
    }

    @RequestMapping(value = "/blocked",method = RequestMethod.GET)
    public ModelAndView blockedUsers(ModelAndView model) {
        List<UserEntity> userEntities = userService.getBlockedUsers();
        model.addObject("blockedUsers", userEntities);
        model.setViewName("admin/blockedUsers");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        UserEntity userEntity = new UserEntity();
        model.addObject("user", userEntity);
        model.setViewName("admin/addUser");

        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute UserEntity userEntity) {
        if (userEntity.getId() == 0) { // if employee id is 0 then creating the
            userService.addUser(userEntity);
        } else {
            UserEntity tmp = userService.getById(userEntity.getId());
            tmp.setUsername(userEntity.getUsername());
            tmp.setFullName(userEntity.getFullName());
            userService.update(tmp);
        }

        return new ModelAndView("redirect:/user");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable(value = "id") Long userId) {
        userService.delete(userId);

        return new ModelAndView("redirect:/user");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editUserById(@PathVariable(value = "id") Long userId) {
        UserEntity userEntity = userService.getById(userId);
        ModelAndView model = new ModelAndView();
        model.addObject("userEdit", userEntity);
        model.setViewName("editProfile");

        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        String login = request.getUserPrincipal().getName();
        UserEntity userEntity = userService.getByUserName(login);
        ModelAndView model = new ModelAndView();
        model.addObject("userEdit", userEntity);
        model.setViewName("editProfile");

        return model;
    }

    @RequestMapping(value = "{id}/info", method = RequestMethod.GET)
    public ModelAndView userInfo(@PathVariable(value = "id") Long userId) {
        ModelAndView model = new ModelAndView();
        UserEntity userEntity = userService.getById(userId);
        List<ContractEntity> contractEntities = userEntity.getContractEntities();
        List<PaymentEntity> paymentEntities = userEntity.getPaymentEntities();

        model.addObject("listContracts", contractEntities);
        model.addObject("listPayments", paymentEntities);
        model.addObject("user", userEntity);
        model.setViewName("admin/userInfo");

        return model;
    }

    @RequestMapping(value = "{id}/payments", method = RequestMethod.GET)
    public ModelAndView userPayments(@PathVariable(value = "id") Long userId,ModelAndView model) {
        UserEntity userEntity = userService.getById(userId);
        List<PaymentEntity> paymentEntities = userEntity.getPaymentEntities();

        model.addObject("listPayments", paymentEntities);
        model.addObject("user", userEntity);
        model.setViewName("admin/paymentsUser");

        return model;
    }

    @RequestMapping(value = "{id}/changerole", method = RequestMethod.GET)
    public String blockOrUnblock(@PathVariable(value = "id") Long userId) {
        UserEntity user = userService.getById(userId);
        if(Role.ROLE_USER_ACTIVE.name().equals(user.getRole())){
            user.setRole(Role.ROLE_USER_BLOCKED);
            userService.changeRole(user);
            return "redirect:/user/unpaid";
        }
        else if(Role.ROLE_USER_BLOCKED.name().equals(user.getRole())){
            user.setRole(Role.ROLE_USER_ACTIVE);
            userService.changeRole(user);
            return "redirect:/user/blocked";
        }
        return "";
    }
}
