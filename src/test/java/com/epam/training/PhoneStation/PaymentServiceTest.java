package com.epam.training.PhoneStation;

import com.epam.training.PhoneStation.entity.CallEntity;
import com.epam.training.PhoneStation.entity.PaymentEntity;
import com.epam.training.PhoneStation.service.api.CallService;
import com.epam.training.PhoneStation.service.api.PaymentService;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentServiceTest extends AbstractTest{

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CallService callService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/payment/TestPaymentService.initData.xml")
    public void testGetPaymentById(){

        PaymentEntity payment = paymentService.getById(1L);

        Assert.assertNotNull(payment.getContract());
        Assert.assertNull(payment.getCall());
        Assert.assertEquals(payment.getUser().getId(),1L);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/payment/TestPaymentService.initData.xml")
    public void testGetAllPayment(){

        List<PaymentEntity> payments = paymentService.getAll();

        Assert.assertEquals(payments.size(),3);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/payment/TestPaymentService.initData.xml")
    public void testGetAllPaymentByUser(){

        List<PaymentEntity> payments = paymentService.getAllByUser(2L);

        Assert.assertEquals(payments.size(),1);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/payment/TestPaymentService.initDataToSavePayment.xml")
    public void testAddPayment(){

        CallEntity call = callService.gedById(1L);

        PaymentEntity payment = paymentService.addPayment("test", call);

        Assert.assertNotNull(payment.getCall());
        Assert.assertEquals(payment.getUser().getUsername(),"test");

    }


    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/payment/TestPaymentService.initData.xml")
    public void testPay(){

        PaymentEntity payment = paymentService.getById(1L);
        paymentService.pay(payment);

        payment = paymentService.getById(1L);

        Assert.assertNotNull(payment.getPaymentDate());
        Assert.assertTrue(payment.getPaid());

    }
    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/payment/TestPaymentService.initData.xml")
    public void testPaymentDelete(){

        PaymentEntity payment = paymentService.getById(1L);
        paymentService.delete(payment);

        payment = paymentService.getById(1L);

        Assert.assertNull(payment);
    }

}
