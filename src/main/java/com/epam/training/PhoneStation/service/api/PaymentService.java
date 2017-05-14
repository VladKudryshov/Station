package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.entity.PaymentEntity;
import com.epam.training.PhoneStation.entity.UserEntity;

import java.util.List;

public interface PaymentService {

    PaymentEntity getById(long id);

    List<PaymentEntity> getAll();

    List<PaymentEntity> getAllByUser(long userId);

    PaymentEntity addPayment(String username, Object model);

    void pay(PaymentEntity paymentEntity);

    void delete(PaymentEntity payment);

}
