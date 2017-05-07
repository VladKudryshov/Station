package com.epam.training.PhoneStation.dao.api;

import com.epam.training.PhoneStation.entity.CallEntity;
import com.epam.training.PhoneStation.entity.UserEntity;

import java.util.List;

public interface CallDao extends AbstractDao<CallEntity>{

    List<CallEntity> getAll();


}
