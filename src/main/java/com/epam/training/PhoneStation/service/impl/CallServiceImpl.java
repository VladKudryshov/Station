package com.epam.training.PhoneStation.service.impl;


import com.epam.training.PhoneStation.dao.api.CallDao;
import com.epam.training.PhoneStation.dao.api.ServiceModelDao;
import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.model.Call;
import com.epam.training.PhoneStation.model.ServiceModel;
import com.epam.training.PhoneStation.model.User;
import com.epam.training.PhoneStation.service.api.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;

@Service
@Transactional
public class CallServiceImpl implements CallService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CallDao callDao;

    @Autowired
    private ServiceModelDao serviceModelDao;

    @Override
    @Transactional
    public Call addCall(long userId, Time start, Time end) {
        User user = userDao.getById(userId);
        ServiceModel serviceModel = serviceModelDao.getById(1);

        long seconds = (end.getTime()-start.getTime())/1000;
        Time time =new Time(0,0,(int)seconds);

        int cost = (int)Math.ceil(seconds/60.0)*serviceModel.getCost();

        Call call = new Call();
        call.setTime(time);
        call.setUser(user);
        call.setCost(cost);

        return callDao.save(call);
    }
}
