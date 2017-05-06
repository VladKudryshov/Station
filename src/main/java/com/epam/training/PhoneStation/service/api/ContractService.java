package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.entity.ContractEntity;

public interface ContractService {

    ContractEntity getContract(long id);

    void addContract(String userName, long serviceId);

    void update(ContractEntity contract);

}
