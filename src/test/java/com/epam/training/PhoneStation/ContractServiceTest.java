package com.epam.training.PhoneStation;


import com.epam.training.PhoneStation.entity.ContractEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.ContractService;
import com.epam.training.PhoneStation.service.api.ServiceModelService;
import com.epam.training.PhoneStation.service.api.UserService;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ContractServiceTest extends AbstractTest {
    @Autowired
    private ContractService contractService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceModelService serviceModelService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/contract/TestContractService.initData.xml")
    public void testGetContractById() {

        ContractEntity contract = contractService.getById(1L);

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(dateFormat.parse("2017-05-13").getTime());
            Assert.assertEquals(date, contract.getEndDate());
            Assert.assertEquals(1, contract.getService().getId());
            Assert.assertEquals(1, contract.getUser().getId());
        }catch (ParseException e){
            Assert.assertNotNull(null);
        }

    }


    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/contract/TestContractService.initDataManyContracts.xml")
    public void testGetAllContracts() {

        List<ContractEntity> contracts = contractService.getAll();

        Assert.assertEquals(contracts.size(),3);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/contract/TestContractService.initDataManyContracts.xml")
    public void testGetAllContractByUser() {

        List<ContractEntity> contracts = contractService.getAllByUser(1L);

        Assert.assertEquals(contracts.size(),2);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/contract/TestContractService.initDataManyContracts.xml")
    public void testContractUpdate() {

        ContractEntity contract = contractService.getById(1L);
        UserEntity user = userService.getById(2L);
        contract.setUser(user);
        contractService.update(contract);

        contract = contractService.getById(1L);

        Assert.assertEquals(contract.getUser().getId(),2);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/contract/TestContractService.initDataToSaveContract.xml")
    public void testContractSave() {

        ContractEntity contract = contractService.addContract("test",1L);

        Assert.assertEquals(contract.getUser().getId(),1);
        Assert.assertEquals(contract.getService().getId(),1);

    }


}
