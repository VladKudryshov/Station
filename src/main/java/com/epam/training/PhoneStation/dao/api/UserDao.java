package com.epam.training.PhoneStation.dao.api;

import com.epam.training.PhoneStation.model.User;

import java.util.List;

public interface UserDao extends AbstractDao<User>{

    User getByLogin(String login);

    List<User> getAll();

    List<User> getBlockedUser();

}
