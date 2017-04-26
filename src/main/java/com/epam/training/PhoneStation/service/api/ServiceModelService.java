package com.epam.training.PhoneStation.service.api;


import com.epam.training.PhoneStation.model.ServiceModel;
import com.epam.training.PhoneStation.model.User;

import java.util.List;

public interface ServiceModelService {
    ServiceModel addService(ServiceModel serviceModel);

    ServiceModel getById(long id);

    ServiceModel getByTitle(String login);

    void update(ServiceModel serviceModel);

    void delete(long id);

    List<ServiceModel> getAll();
}
