package com.epam.training.PhoneStation.controller.json;

import com.epam.training.PhoneStation.entity.CallEntity;
import com.epam.training.PhoneStation.service.api.CallService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/api/call")
public class CallControllerJson {

    @Autowired
    private CallService callService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,headers="Accept=application/json")
    public CallEntity getById(@PathVariable(value = "id") Long serviceId) {
        return callService.gedById(serviceId);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET,headers="Accept=application/json")
    public List<CallEntity> getAllByUser(@PathVariable(value = "id") Long userId) {
        return callService.getAllByUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CallEntity> getAll() {
        return callService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addCall(@RequestBody String temp) {
        JsonObject obj = new JsonParser().parse(temp).getAsJsonObject();
        obj = obj.getAsJsonObject();
        String userName = obj.get("username").toString();
        String strTime = obj.get("time").toString();
        if(userName==null || strTime==null)return new ResponseEntity(HttpStatus.BAD_REQUEST);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            Time time = new Time(sdf.parse(strTime.replace("\"","")).getTime());
            callService.addCall(userName,time);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
