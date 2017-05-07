package com.epam.training.PhoneStation.dao.api;

import com.epam.training.PhoneStation.entity.ContractEntity;

import java.util.List;

public interface ContractDao extends AbstractDao<ContractEntity> {

    List<ContractEntity> getAll();

}
