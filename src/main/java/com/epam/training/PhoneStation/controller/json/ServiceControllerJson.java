package com.epam.training.PhoneStation.controller.json;

import com.epam.training.PhoneStation.entity.Error;
import com.epam.training.PhoneStation.entity.ServiceEntity;
import com.epam.training.PhoneStation.service.api.ServiceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/service")
public class ServiceControllerJson {

    @Autowired
    private ServiceModelService serviceModelService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,headers="Accept=application/json")
    public ServiceEntity getById(@PathVariable(value = "id") Long serviceId) {
        return serviceModelService.getById(serviceId);
    }

    @RequestMapping(method = RequestMethod.GET,headers="Accept=application/json")
    public List<ServiceEntity> getAll() {
        return serviceModelService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody ServiceEntity service) {
        serviceModelService.addService(service);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,headers="Accept=application/json")
    public ResponseEntity  updateUser(@RequestBody ServiceEntity serviceEntity,@PathVariable(value = "id") Long serviceId) {
        ServiceEntity service = serviceModelService.getById(serviceId);

        if (serviceEntity.getTitleEn()!=null && !serviceEntity.getTitleEn().isEmpty())service.setTitleEn(serviceEntity.getTitleEn());
        if (serviceEntity.getTitleRu()!=null && !serviceEntity.getTitleRu().isEmpty())service.setTitleRu(serviceEntity.getTitleRu());

        service.setCost(serviceEntity.getCost());
        service.setPeriod(serviceEntity.getPeriod());

        serviceModelService.update(service);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity  deleteUser(@PathVariable(value = "id") Long serviceId) {
        ServiceEntity service = serviceModelService.getById(serviceId);
        try{
            if(service!=null)serviceModelService.delete(serviceId);
        }catch (Exception e ){
            Error error = new Error();
            error.setError("Service not deleted, because users use this service");
            return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
