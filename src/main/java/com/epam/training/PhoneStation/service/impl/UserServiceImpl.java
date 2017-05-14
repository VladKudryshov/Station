package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.entity.*;
import com.epam.training.PhoneStation.service.api.UserService;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public UserEntity getByUserName(String login) {
        return userDao.getByUserName(login);
    }

    @Override
    @Transactional
    public UserEntity getById(long id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public List<UserEntity> getAllUser() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public List<UserEntity> getBlockedUsers() {
        return userDao.getBlockedUser();
    }

    @Transactional
    @Override
    public List<UserEntity> getNotPaidUser() {
        Calendar calendar = new GregorianCalendar();
        Date date = new Date(calendar.getTimeInMillis());
        long nowDate = date.getTime();

        List<UserEntity> userForBlock = new ArrayList<>();
        List<UserEntity> users = userDao.getAll();

        for (UserEntity user : users) {

            if(user.getRole().equals(Role.ROLE_USER_BLOCKED.name()))continue;

            boolean isBlock = false;

            List<ContractEntity> contracts = user.getContractEntities();
            for (ContractEntity contract : contracts) {
                PaymentEntity payment = contract.getPayment();

                if (payment.getPaid())continue;

                Date dateContract = contract.getEndDate();
                long endDateContract = dateContract.getTime();

                if(nowDate>endDateContract ){
                    userForBlock.add(user);
                    isBlock = true;
                    break;
                }
            }

            if (isBlock)continue;

            List<CallEntity> calls = user.getCallEntities();
            for (CallEntity call : calls) {
                PaymentEntity payment = call.getPayment();

                if (payment.getPaid())continue;

                Date callDate = call.getDate();
                Calendar tmpCalendar = new GregorianCalendar();
                tmpCalendar.add(Calendar.DAY_OF_MONTH, -30);
                Date tmpDate = new Date(tmpCalendar.getTimeInMillis());

                if(callDate.toString().equals(tmpDate.toString())){
                    userForBlock.add(user);
                    break;
                }
            }
        }

        return userForBlock;
    }

    @Override
    @Transactional
    public UserEntity addUser(UserEntity userEntity) {

        if (userDao.getByUserName(userEntity.getUsername())==null){
            userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
            userEntity = userDao.save(userEntity);

            LOGGER.debug("Add user {}", userEntity);

            return userEntity;
        }

        LOGGER.debug("User already exists {}", userEntity);

        return null;

    }

    @Override
    @Transactional
    public void update(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        LOGGER.info("Update user {}", userEntity);
        userDao.update(userEntity);
    }

    @Override
    public void changeRole(UserEntity userEntity)   {
        LOGGER.info("For user {} change role {}", userEntity.getId(), userEntity.getRole());
        userDao.update(userEntity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        UserEntity userEntity = userDao.getById(id);
        if(userEntity!=null){
            LOGGER.info("Delete user {}", userEntity);
            userDao.delete(userEntity);
        }
    }

}
