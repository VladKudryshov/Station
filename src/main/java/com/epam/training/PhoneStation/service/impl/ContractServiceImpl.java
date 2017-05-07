package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.ContractDao;
import com.epam.training.PhoneStation.dao.api.ServiceEntityDao;
import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.entity.ContractEntity;
import com.epam.training.PhoneStation.entity.ServiceEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.ContractService;
import com.epam.training.PhoneStation.service.api.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class ContractServiceImpl implements ContractService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractServiceImpl.class);

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ServiceEntityDao serviceEntityDao;

    @Autowired
    private PaymentService paymentService;

    @Transactional
    @Override
    public ContractEntity getById(long id) {
        ContractEntity contract = contractDao.getById(id);
        LOGGER.debug("Get contract: {}", contract );
        return contract;
    }

    @Override
    public List<ContractEntity> getAllByUser(long userId) {
        UserEntity user = userDao.getById(userId);
        return user.getContractEntities();
    }

    @Override
    public List<ContractEntity> getAll() {
        return contractDao.getAll();
    }

    @Override
    @Transactional
    public void addContract(String userName, long serviceId) {
        UserEntity userEntity = userDao.getByLogin(userName);
        ServiceEntity service = serviceEntityDao.getById(serviceId);

        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR,service.getPeriod());
        Date dateWithoutTime = new Date(date.getTimeInMillis());

        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setUser(userEntity);
        contractEntity.setService(service);
        contractEntity.setEndDate(dateWithoutTime);

        LOGGER.debug("Add contract: {}", contractEntity);

        ContractEntity result = contractDao.save(contractEntity);
        paymentService.addPayment(userName,result);
    }

    @Override
    @Transactional
    public void update(ContractEntity contract) {
        contractDao.update(contract);

    }
}
