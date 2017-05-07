package com.epam.training.PhoneStation.controller.json;

import com.epam.training.PhoneStation.entity.ContractEntity;
import com.epam.training.PhoneStation.service.api.ContractService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contract")
public class ContractControllerJson {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,headers="Accept=application/json")
    public ContractEntity getById(@PathVariable(value = "id") Long contractId) {
        return contractService.getById(contractId);
    }

    @RequestMapping(value = "user/{id}",method = RequestMethod.GET,headers="Accept=application/json")
    public List<ContractEntity> getAllByUser(@PathVariable(value = "id") Long userId) {
        return contractService.getAllByUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET,headers="Accept=application/json")
    public List<ContractEntity> getAll() {
        return contractService.getAll();
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addContract(@RequestBody String temp) {
        JsonObject obj = new JsonParser().parse(temp).getAsJsonObject();
        obj = obj.getAsJsonObject();
        String userName = obj.get("username").toString();
        String strServiceId = obj.get("serviceId").toString();
        if(userName==null || strServiceId==null)return new ResponseEntity(HttpStatus.BAD_REQUEST);
        long serviceId = Long.valueOf(strServiceId);

        contractService.addContract(userName,serviceId);

        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity  deactivateContractByService(@PathVariable(value = "id") Long contractId) {
        ContractEntity contract = contractService.getById(contractId);
        contract.setUser(null);
        contractService.update(contract);
        return new ResponseEntity(HttpStatus.OK);
    }


}
