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

        for (String str : fullname()) {
            System.out.println(str);
        }

    }

    private static String[] fullname(){

        return new String[]{"имя" , "фамилия", "отчество"};
    }
}
