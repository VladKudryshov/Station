package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.entity.UserEntity;

import java.sql.Date;
import java.util.List;

public interface UserService {

    UserEntity getByLogin(String login);

    UserEntity getById(long id);

    List<UserEntity> getAllUser();

    List<UserEntity> getBlockedUsers();

    List<UserEntity> getNotPaidUser();

    UserEntity addUser(UserEntity userEntity);

    void update(UserEntity userEntity);

    void changeRole(UserEntity userEntity);

    void delete(long id);

}
