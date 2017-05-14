package com.epam.training.PhoneStation;

import com.epam.training.PhoneStation.entity.ServiceEntity;
import com.epam.training.PhoneStation.service.api.ServiceModelService;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceEntiteServiceTest extends AbstractTest{
    @Autowired
    private ServiceModelService serviceModelService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/service/TestServiceModelService.initData.xml")
    public void testGetServiceById(){

        ServiceEntity service = serviceModelService.getById(1L);

        Assert.assertEquals(12, service.getCost());
        Assert.assertEquals(12, service.getPeriod());
        Assert.assertEquals("MTS", service.getTitleEn());
        Assert.assertEquals("МТС", service.getTitleRu());

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/service/TestServiceModelService.initDataAll.xml")
    public void testGetAllService(){

        List<ServiceEntity> services = serviceModelService.getAll();

        Assert.assertEquals(services.size(),3);

    }

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,value = "/service/TestServiceModelService.expectedData.xml")
    public void testServiceSave(){

        ServiceEntity service = new ServiceEntity();

        service.setCost(12);
        service.setPeriod(12);
        service.setTitleEn("Life!)");
        service.setTitleRu("Лайф");

        serviceModelService.addService(service);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/service/TestServiceModelService.initData.xml")
    public void testServiceUpdate(){

        ServiceEntity service = serviceModelService.getById(1L);
        service.setPeriod(20);
        serviceModelService.update(service);
        service = serviceModelService.getById(1L);

        Assert.assertEquals(service.getPeriod(),20);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/service/TestServiceModelService.initData.xml")
    public void testServiceDelete(){

        serviceModelService.delete(1L);

        ServiceEntity service = serviceModelService.getById(1L);

        Assert.assertNull(service);

    }




}
