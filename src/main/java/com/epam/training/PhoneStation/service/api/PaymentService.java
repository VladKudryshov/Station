package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.model.Payment;
import com.epam.training.PhoneStation.model.User;

public interface PaymentService {

    Payment addPayment(User user, Object model);

    void pay(Payment payment, int cost);


}
