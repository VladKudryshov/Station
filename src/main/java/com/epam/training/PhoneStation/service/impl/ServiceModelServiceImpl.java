package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.ServiceEntityDao;
import com.epam.training.PhoneStation.entity.ServiceEntity;
import com.epam.training.PhoneStation.service.api.ServiceModelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceModelServiceImpl implements ServiceModelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceModelServiceImpl.class);

    @Autowired
    private ServiceEntityDao serviceEntityDao;

    @Override
    @Transactional
    public ServiceEntity getById(long id) {
       return serviceEntityDao.getById(id);
    }

    @Override
    @Transactional
    public List<ServiceEntity> getAll() {
        return serviceEntityDao.getAll();
    }

    @Override
    @Transactional
    public void addService(ServiceEntity serviceEntity) {
        serviceEntityDao.save(serviceEntity);
        LOGGER.debug("Add service = {}", serviceEntity);
    }

    @Override
    @Transactional
    public void update(ServiceEntity serviceEntity) {
        LOGGER.info("Service {} update", serviceEntity.getTitleEn());
        serviceEntityDao.update(serviceEntity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        ServiceEntity serviceEntity = serviceEntityDao.getById(id);
        if(serviceEntity!=null){
            LOGGER.info("Delete service id = {}", serviceEntity.getId());
            serviceEntityDao.delete(serviceEntity);
        }
    }

}
