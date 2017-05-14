package com.epam.training.PhoneStation;

import com.epam.training.PhoneStation.entity.CallEntity;
import com.epam.training.PhoneStation.entity.ServiceEntity;
import com.epam.training.PhoneStation.service.api.CallService;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CallServiceTest extends AbstractTest{
    @Autowired
    private CallService callService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/call/TestCallService.initData.xml")
    public void testGetCallById(){

        CallEntity call = callService.gedById(1L);

        try{

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date nowDate = new Date(format.parse("2017-05-14").getTime());

            format = new SimpleDateFormat("hh:mm:ss");
            Time callTime = new Time(format.parse("00:00:13").getTime());

            Assert.assertEquals(call.getCost(), 100);
            Assert.assertEquals(call.getDate(), nowDate);
            Assert.assertEquals(call.getTime(), callTime);
            Assert.assertEquals(call.getUser().getId(), 1L);

        }catch (ParseException e){
            Assert.assertNotNull(null);
        }

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/call/TestCallService.initDataManyCalls.xml")
    public void testGetAllCalls(){

        List<CallEntity> calls = callService.getAll();

        Assert.assertEquals(calls.size(),5);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/call/TestCallService.initDataManyCalls.xml")
    public void testGetAllCallsByUser(){

        List<CallEntity> calls = callService.getAllByUser(1L);

        Assert.assertEquals(calls.size(),3);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/call/TestCallService.initDataToSaveCall.xml")
    public void testCallSave(){

        try{

            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
            Time callTime = new Time(format.parse("00:00:13").getTime());

            CallEntity call = callService.addCall("test", callTime);

            Assert.assertEquals(call.getUser().getUsername(),"test");
            Assert.assertEquals(call.getTime(),callTime);

        }catch (ParseException e){
            Assert.assertNotNull(null);
        }

    }


}
