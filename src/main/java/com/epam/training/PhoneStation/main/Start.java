package com.epam.training.PhoneStation.main;

import com.epam.training.PhoneStation.entity.PaymentEntity;
import com.epam.training.PhoneStation.entity.Role;
import com.epam.training.PhoneStation.entity.ServiceEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Start {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("servlet-context.xml");

        UserService userDao = context.getBean(UserService.class);
        ServiceModelService serviceDao = context.getBean(ServiceModelService.class);
        CallService service = context.getBean(CallService.class);
        ContractService contractService = context.getBean(ContractService.class);
        PaymentService paymentService = context.getBean(PaymentService.class);

        UserEntity user = new UserEntity();
        user.setUsername("vlad1");
        user.setPassword("123");
        user.setRole(Role.ROLE_ADMIN);
        user = userDao.addUser(user);

    }

}
