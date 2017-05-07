package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.CallDao;
import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.entity.CallEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.CallService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class CallServiceImpl implements CallService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallServiceImpl.class);

    @Autowired
    private CallDao callDao;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public CallEntity addCall(String username, Time time) {
        Calendar date = new GregorianCalendar();

        UserEntity user = userDao.getByLogin(username);

        CallEntity call = new CallEntity();
        call.setUser(user);
        call.setTime(time);
        call.setDate(new Date(date.getTimeInMillis()));
        call.setCost(123);

        LOGGER.debug("Add call: {}",call);

        return callDao.save(call);
    }

    @Override
    @Transactional
    public CallEntity gedById(long callId) {
        return callDao.getById(callId);
    }

    @Override
    @Transactional
    public List<CallEntity> getAllByUser(long userId) {
        UserEntity user = userDao.getById(userId);
        return user.getCallEntities();
    }

    @Override
    @Transactional
    public List<CallEntity> getAll() {
        return callDao.getAll();
    }
}
