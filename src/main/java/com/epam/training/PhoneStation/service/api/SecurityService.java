package com.epam.training.PhoneStation.service.api;

public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
