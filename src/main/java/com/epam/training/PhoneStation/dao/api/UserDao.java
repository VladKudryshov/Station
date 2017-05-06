package com.epam.training.PhoneStation.dao.api;

import com.epam.training.PhoneStation.entity.UserEntity;

import java.util.List;

public interface UserDao extends AbstractDao<UserEntity>{

    UserEntity getByLogin(String login);

    List<UserEntity> getAll();

    List<UserEntity> getBlockedUser();

}
