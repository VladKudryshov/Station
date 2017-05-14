package com.epam.training.PhoneStation;

import com.epam.training.PhoneStation.entity.Role;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.UserService;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserEntityServiceTest extends AbstractTest{
    @Autowired
    private UserService userService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/user/TestUserService.initData.xml")
    public void testGetUserById(){

        UserEntity user = userService.getById(1L);

        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("Dave", user.getFullName());
        Assert.assertEquals("ROLE_USER_ACTIVE", user.getRole());

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/user/TestUserService.initData.xml")
    public void testGetUserByLogin(){

        UserEntity user = userService.getByUserName("test");

        Assert.assertNotNull(user);
        Assert.assertEquals("Dave", user.getFullName());
        Assert.assertEquals("ROLE_USER_ACTIVE", user.getRole());

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/user/TestUserService.initDataBlockedUser.xml")
    public void testGetBlockedUsers(){

        List<UserEntity> blockedUsers = userService.getBlockedUsers();

        Assert.assertEquals(blockedUsers.size(),2);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/user/TestUserService.initDataUnpaidUser.xml")
    public void testGetUnpaidUsers(){

        List<UserEntity> blockedUsers = userService.getNotPaidUser();

        Assert.assertNotEquals(blockedUsers.size(),0);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/user/TestUserService.initDataBlockedUser.xml")
    public void testGetAllUser(){

        List<UserEntity> blockedUsers = userService.getAllUser();

        Assert.assertEquals(blockedUsers.size(),3);

    }

    @Test
    @ExpectedDatabase(value = "/user/TestUserService.expectedData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUserSave(){

        UserEntity user = new UserEntity();

        user.setFullName("Dave");
        user.setUsername("test");
        user.setPassword("123");
        user.setRole(Role.ROLE_USER_BLOCKED);

        userService.addUser(user);

    }

    @Test
    @DatabaseSetup(type=DatabaseOperation.INSERT ,value = "/user/TestUserService.initData.xml")
    public void testUserSameUsernameSave(){

        UserEntity user = new UserEntity();

        user.setFullName("Dave");
        user.setUsername("test");
        user.setPassword("123");
        user.setRole(Role.ROLE_USER_ACTIVE);

        user = userService.addUser(user);

        Assert.assertNull(user);

    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/user/TestUserService.initData.xml")
    public void testUserUpdate(){

        UserEntity user = userService.getById(1L);
        user.setRole(Role.ROLE_USER_BLOCKED);
        userService.update(user);
        user = userService.getById(1L);

        Assert.assertEquals(user.getRole(),"ROLE_USER_BLOCKED");

    }

    @Test
    @DatabaseSetup(type=DatabaseOperation.INSERT ,value = "/user/TestUserService.initData.xml")
    public void testUserDelete(){

        userService.delete(1L);
        UserEntity user = userService.getById(1L);

        Assert.assertNull(user);

    }





}
