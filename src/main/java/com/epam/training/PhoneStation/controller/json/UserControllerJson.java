package com.epam.training.PhoneStation.controller.json;

import com.epam.training.PhoneStation.entity.Role;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserControllerJson {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,headers="Accept=application/json")
    public UserEntity getById(@PathVariable(value = "id") Long userId) {
        return userService.getById(userId);
    }

    @RequestMapping(method = RequestMethod.GET,headers="Accept=application/json")
    public List<UserEntity> getAll() {
        return userService.getAllUser();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody UserEntity user) {
        userService.addUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,headers="Accept=application/json")
    public @ResponseBody ResponseEntity  updateUser(@RequestBody UserEntity userEntity,@PathVariable(value = "id") Long userId) {
        UserEntity user = userService.getById(userId);
        if(!userEntity.getUsername().isEmpty()) user.setUsername(userEntity.getUsername());
        if(!userEntity.getPassword().isEmpty()) user.setUsername(userEntity.getUsername());
        if(!userEntity.getRole().isEmpty()) user.setRole(Role.valueOf(userEntity.getRole()));
        userService.update(user);

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity  deleteUser(@PathVariable(value = "id") Long userId) {
        UserEntity user = userService.getById(userId);
        if(user!=null)userService.delete(userId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/unpaid", method = RequestMethod.GET, headers="Accept=application/json")
    public List<UserEntity>  unpaidUser() {
        return userService.getNotPaidUser();
    }

    @RequestMapping(value = "/blocked",method = RequestMethod.GET, headers="Accept=application/json")
    public List<UserEntity> blockedUsers() {
        return userService.getBlockedUsers();
    }


}
