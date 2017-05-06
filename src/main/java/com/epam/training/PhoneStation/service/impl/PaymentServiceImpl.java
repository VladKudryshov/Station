package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.PaymentDao;
import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.entity.*;
import com.epam.training.PhoneStation.service.api.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public PaymentEntity getById(long id) {
        return paymentDao.getById(id);
    }

    @Override
    @Transactional
    public List<PaymentEntity> getAll() {
        return paymentDao.getAll();
    }

    @Override
    @Transactional
    public void addPayment(String username, Object model) {

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setUser(userDao.getByLogin(username));

        if(model instanceof ContractEntity) {
            paymentEntity.setContract((ContractEntity) model);
        }else {
            paymentEntity.setCall((CallEntity) model);
        }
        paymentEntity.setPaid(false);

        paymentEntity = paymentDao.save(paymentEntity);

        LOGGER.debug("Add payment: {}",paymentEntity);
    }

    @Override
    @Transactional
    public void pay(PaymentEntity paymentEntity) {

        int cost;
        if(paymentEntity.getContract()!= null){
            ContractEntity contract = paymentEntity.getContract();
            ServiceEntity service = contract.getService();
            cost = service.getCost();
        }else if(paymentEntity.getCall()!= null){
            CallEntity callEntity = paymentEntity.getCall();
            cost = callEntity.getCost();
        }else return;


        Calendar date = new GregorianCalendar();
        Date dateWithoutTime = new Date(date.getTimeInMillis());
        paymentEntity.setPaymentDate(dateWithoutTime);


        paymentEntity.setCost(cost);
        paymentEntity.setPaid(true);

        LOGGER.info("The user {} paid", paymentEntity.getUser().getFullName());
        paymentDao.update(paymentEntity);
    }

}
