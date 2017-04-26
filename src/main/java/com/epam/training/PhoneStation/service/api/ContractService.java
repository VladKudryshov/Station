package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.model.Contract;

public interface ContractService {

    void addContract(long userId, long serviceId);

    Contract getContract(long id);

}
