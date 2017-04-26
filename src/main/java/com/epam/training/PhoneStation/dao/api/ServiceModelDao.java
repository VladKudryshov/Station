package com.epam.training.PhoneStation.dao.api;

import com.epam.training.PhoneStation.model.ServiceModel;

import java.util.List;

public interface ServiceModelDao extends AbstractDao<ServiceModel>{
    List<ServiceModel> getAll();
}
