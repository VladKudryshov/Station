package com.epam.training.PhoneStation.dao.api;

import com.epam.training.PhoneStation.entity.ServiceEntity;

import java.util.List;

public interface ServiceEntityDao extends AbstractDao<ServiceEntity>{
    List<ServiceEntity> getAll();
}
