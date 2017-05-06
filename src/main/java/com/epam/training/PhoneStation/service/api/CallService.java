package com.epam.training.PhoneStation.service.api;

import com.epam.training.PhoneStation.entity.CallEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.sql.Time;
import java.util.List;

public interface    CallService {

    CallEntity addCall(String username, Time time);

}
