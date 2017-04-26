package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.PaymentDao;
import com.epam.training.PhoneStation.dao.api.ServiceModelDao;
import com.epam.training.PhoneStation.model.Call;
import com.epam.training.PhoneStation.model.Payment;
import com.epam.training.PhoneStation.model.ServiceModel;
import com.epam.training.PhoneStation.model.User;
import com.epam.training.PhoneStation.service.api.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Payment addPayment(User user, Object model) {

        Payment payment = new Payment();
        payment.setUser(user);

        if(model instanceof ServiceModel) {
            payment.setService((ServiceModel) model);
        }else {
            payment.setCall((Call) model);
        }
        payment.setPaid(false);

        return paymentDao.save(payment);
    }

    @Override
    public void pay(Payment payment, int cost) {

        int price;
        if(payment.getService()!= null){
            ServiceModel serviceModel = payment.getService();
            price = serviceModel.getCost();
        }else if(payment.getCall()!= null){
            Call call = payment.getCall();
            price = call.getCost();
        }else return;


        Calendar date = new GregorianCalendar();
        Date dateWithoutTime = new Date(date.getTimeInMillis());
        payment.setPaymentDate(dateWithoutTime);

        int nowCost = payment.getCost()+cost;
        payment.setCost(nowCost);

        if(nowCost>=price) {
            payment.setPaid(true);
        }

        System.out.println(payment);
        paymentDao.update(payment);
    }
}
