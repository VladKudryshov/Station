package com.epam.training.PhoneStation;


import com.epam.training.PhoneStation.entity.ServiceEntity;
import com.epam.training.PhoneStation.service.api.ServiceModelService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceEntityServiceTest {
    @Autowired
    private ServiceModelService serviceModelService;

    private ServiceEntity serviceEntity = new ServiceEntity();


    @Before
    public void init(){
        serviceEntity.setTitleEn("MTS");
        serviceEntity.setTitleRu("МТС");
        serviceEntity.setCost(100);
        serviceEntity.setPeriod(10);
    }

    @Test
    public void testAddService()
    {
        serviceEntity = serviceModelService.addService(serviceEntity);
        ServiceEntity tmp = serviceModelService.getById(serviceEntity.getId());
        Assert.assertEquals(serviceEntity, tmp);
    }

    @Test
    public void testGetService(){
        serviceEntity = serviceModelService.getById(1);
        System.out.println(serviceEntity);
        Assert.assertNotNull(serviceEntity);
    }

    @Test
    public void testDeleteService(){
        serviceEntity = serviceModelService.getById(1);
        serviceModelService.delete(serviceEntity.getId());
        serviceEntity = serviceModelService.getById(1);

        Assert.assertNull(serviceEntity);
    }
    @Test
    public void testUpdateService(){
        int period = 30;
        serviceEntity = serviceModelService.getById(1);
        System.out.println(serviceEntity);
        serviceEntity.setPeriod(period);
        serviceModelService.update(serviceEntity);
        ServiceEntity tmp = serviceModelService.getById(1);
        Assert.assertEquals(period, tmp.getPeriod());
    }



}
