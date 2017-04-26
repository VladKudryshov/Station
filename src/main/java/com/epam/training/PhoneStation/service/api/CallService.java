package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.model.Call;

import java.sql.Time;

public interface CallService {

    Call addCall(long userId, Time start, Time end);


}
