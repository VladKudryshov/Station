package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.entity.ContractEntity;

import java.util.List;

public interface ContractService {

    ContractEntity getById(long id);

    List<ContractEntity> getAllByUser(long userId);

    List<ContractEntity> getAll();

    ContractEntity addContract(String userName, long serviceId);

    void update(ContractEntity contract);

}
