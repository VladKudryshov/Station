package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.ContractDao;
import com.epam.training.PhoneStation.dao.api.ServiceModelDao;
import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.model.Contract;
import com.epam.training.PhoneStation.model.ServiceModel;
import com.epam.training.PhoneStation.model.User;
import com.epam.training.PhoneStation.service.api.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

@Service
@Transactional
public class ContractServiceImpl implements ContractService{
    @Autowired
    private ContractDao contractDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ServiceModelDao serviceModelDao;

    @Override
    @Transactional
    public void addContract(long userId, long serviceId) {
        User user = userDao.getById(userId);
        ServiceModel service = serviceModelDao.getById(serviceId);

        Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR,service.getPeriod());
        Date dateWithoutTime = new Date(date.getTimeInMillis());

        Contract contract = new Contract();
        contract.setUser(user);
        contract.setService(service);
        contract.setEndDate(dateWithoutTime);

        contractDao.save(contract);
    }

    @Override
    @Transactional
    public Contract getContract(long id) {
        return contractDao.getById(id);
    }
}
