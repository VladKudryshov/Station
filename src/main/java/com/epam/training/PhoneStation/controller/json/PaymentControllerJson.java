package com.epam.training.PhoneStation.controller.json;

import com.epam.training.PhoneStation.entity.PaymentEntity;
import com.epam.training.PhoneStation.service.api.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentControllerJson {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,headers="Accept=application/json")
    public PaymentEntity getById(@PathVariable(value = "id") Long paymentId) {
        return paymentService.getById(paymentId);
    }

    @RequestMapping(value = "user/{id}",method = RequestMethod.GET,headers="Accept=application/json")
    public List<PaymentEntity> getAllByUser(@PathVariable(value = "id") Long userId) {
        return paymentService.getAllByUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET,headers="Accept=application/json")
    public List<PaymentEntity> getAll() {
        return paymentService.getAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity  deactivateContractByService(@PathVariable(value = "id") Long paymentId) {
        PaymentEntity payment = paymentService.getById(paymentId);
        if (payment!=null)paymentService.delete(payment);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity  pay(@PathVariable(value = "id") Long paymentId) {
        PaymentEntity payment = paymentService.getById(paymentId);
        paymentService.pay(payment);
        return new ResponseEntity(HttpStatus.OK);
    }
}
