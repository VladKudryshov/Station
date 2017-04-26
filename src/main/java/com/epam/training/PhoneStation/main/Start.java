package com.epam.training.PhoneStation.main;

import com.epam.training.PhoneStation.model.Payment;
import com.epam.training.PhoneStation.model.Role;
import com.epam.training.PhoneStation.model.ServiceModel;
import com.epam.training.PhoneStation.model.User;
import com.epam.training.PhoneStation.service.api.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Start {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("servlet-context.xml");

        UserService userDao = context.getBean(UserService.class);
        ServiceModelService serviceDao = context.getBean(ServiceModelService.class);
        CallService service = context.getBean(CallService.class);
        ContractService contractService = context.getBean(ContractService.class);
        PaymentService paymentService = context.getBean(PaymentService.class);




        //paymentService.pay(userId1.getPayments().get(0),7403);
        //ServiceModel calls = userId1.getContracts().get(0).getService();
        //paymentService.addPayment(userId1,calls);

       /* Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_MONTH, 29);
        Date nowDate = new Date(date.getTimeInMillis());
        userDao.blockOrUnblock(userId1,nowDate);*/



        User user = new User();
        user.setUsername("vlad");
        user.setPassword("123");
        user.setRole(Role.ROLE_USER);
        userDao.addUser(user);

        user = new User();
        user.setUsername("vlad1");
        user.setPassword("123");
        user.setRole(Role.ROLE_ADMIN);
        user = userDao.addUser(user);


      /* /*

*/
        ServiceModel services = new ServiceModel();
        services.setTitle("velcom");
        services.setCost(123);
        services.setPeriod(30);
        services = serviceDao.addService(services);

        //contractService.addContract(1,1);

        paymentService.addPayment(user,services);

        user = userDao.getByLogin("vlad1");

        Payment payment = user.getPayments().get(0);

        paymentService.pay(payment,100);
       // System.out.println(serviceDao.getById(1));

        /*Calendar date = new GregorianCalendar();

        Time start = new Time(date.getTimeInMillis());
        date.add(Calendar.HOUR,1);
        date.add(Calendar.SECOND,5);
        Time end = new Time(date.getTimeInMillis());
/**/
        //System.out.println(service.addCall(user.getId(),start,end));
        //contractService.addContract(2,1);




        /*if(userWithLogin == null){*/

        /*}else {
            System.out.println("Login is been used");
        }

        SubscriberService subscriber = new SubscriberService();
        subscriber.setFullName("Kudryshov Vladislav Vitalievich");
        subscriber.setAddress("c. Hrodno");
        subscriber.setUser(user);
        subscriber.setBlocked(false);

        subscriberDao.save(subscriber);*/
    }
}
