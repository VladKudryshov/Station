package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.ServiceModelDao;
import com.epam.training.PhoneStation.model.ServiceModel;
import com.epam.training.PhoneStation.service.api.ServiceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceModelServiceImpl implements ServiceModelService {
    @Autowired
    private ServiceModelDao serviceModelDao;

    @Override
    public ServiceModel getById(long id) {
       return serviceModelDao.getById(id);
    }

    @Override
    public ServiceModel getByTitle(String login) {
        return null;
    }

    @Override
    public void update(ServiceModel serviceModel) {

    }

    @Override
    public void delete(long id) {
        ServiceModel serviceModel = serviceModelDao.getById(id);
        serviceModelDao.delete(serviceModel);
    }

    @Override
    public List<ServiceModel> getAll() {
        return serviceModelDao.getAll();
    }

    @Override
    @Transactional
    public ServiceModel addService(ServiceModel serviceModel) {
        return serviceModelDao.save(serviceModel);
    }
}
