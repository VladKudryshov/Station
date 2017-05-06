package com.epam.training.PhoneStation.dao.api;

import com.epam.training.PhoneStation.entity.PaymentEntity;
import com.epam.training.PhoneStation.entity.UserEntity;

import java.util.List;

public interface PaymentDao extends AbstractDao<PaymentEntity>{

    List<PaymentEntity> getAll();

}
