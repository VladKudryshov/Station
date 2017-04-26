package com.epam.training.PhoneStation.service.impl;

import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.model.Contract;
import com.epam.training.PhoneStation.model.Payment;
import com.epam.training.PhoneStation.model.Role;
import com.epam.training.PhoneStation.model.User;
import com.epam.training.PhoneStation.service.api.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    @Transactional
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = userDao.getById(id);
        userDao.delete(user);
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        return userDao.getAll();
    }

    @Override
    public List<User> getBlockedUser() {
       return userDao.getBlockedUser();
    }

    @Override
    public void blockOrUnblock(User user, Date nowDate) {

        List<Payment> payments = user.getPayments();
        List<Contract> contracts = user.getContracts();
        int indexService = 0;
        boolean flagBlock = false;

        for (Payment payment : payments) {
            if(payment.getService()!=null){
                for (int i = indexService; i<contracts.size(); i++){
                    if(contracts.get(i).getService().getId() == payment.getService().getId()){
                        indexService = i;
                        break;
                    }
                }
                Date dateContract = contracts.get(indexService).getEndDate();

                if(nowDate.getTime()>=dateContract.getTime() && !payment.isPaid()){
                    flagBlock = true;
                    break;
                }
            }

            else{
                if(!payment.isPaid()){
                    flagBlock = true;
                    break;
                }
            }
        }

       /* if(flagBlock){
            user.setRole(Role.USERBLOCKED);
        }else {
            user.setRole(Role.USERACTIVE);
        }*/
        userDao.update(user);
    }


}
