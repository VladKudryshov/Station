package com.epam.training.PhoneStation.service.api;


import com.epam.training.PhoneStation.entity.ServiceEntity;

import java.util.List;

public interface ServiceModelService {

    ServiceEntity addService(ServiceEntity serviceEntity);

    ServiceEntity getById(long id);

    void update(ServiceEntity serviceEntity);

    void delete(long id);

    List<ServiceEntity> getAll();

}
