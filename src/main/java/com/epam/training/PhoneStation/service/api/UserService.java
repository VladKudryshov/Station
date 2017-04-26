package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.model.User;

import java.sql.Date;
import java.util.List;

public interface UserService {

    User addUser(User user);

    User getByLogin(String login);

    User getById(long id);

    void update(User user);

    void delete(long id);

    List<User> getAllUser();

    List<User> getBlockedUser();

    void blockOrUnblock(User user, Date nowDate);

}
