package com.epam.training.PhoneStation;


import com.epam.training.PhoneStation.entity.Role;
import com.epam.training.PhoneStation.entity.UserEntity;
import com.epam.training.PhoneStation.service.api.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserEntityServiceTest {
    @Autowired
    private UserService userService;

    private UserEntity userEntity = new UserEntity();


    @Before
    public void init(){
        userEntity.setUsername("sash1111112a");
        userEntity.setPassword("123");
        userEntity.setRole(Role.ROLE_USER_ACTIVE);
    }

    @Test
    public void testAddUser()
    {

        System.out.println(userEntity);
        userEntity = userService.addUser(userEntity);

        UserEntity tmp = userService.getById(userEntity.getId());
        Assert.assertEquals(userEntity.getId(), tmp.getId());
    }

    @Test
    public void testGetUser(){
        UserEntity userEntity1 = userService.getByLogin(userEntity.getUsername());
        Assert.assertNotNull(userEntity1);
    }

    @Test
    public void testDeleteUser(){
        userEntity = userService.getByLogin(userEntity.getUsername());
        userService.delete(userEntity.getId());
        System.out.println(userService.getByLogin(userEntity.getUsername()));
        userEntity = userService.getByLogin(userEntity.getUsername());

        Assert.assertNull(userEntity);
    }
    @Test
    public void testUpdateUser(){
        UserEntity userEntity1 = userService.getByLogin(userEntity.getUsername());
        System.out.println(userEntity1);
        userEntity1.setRole(Role.ROLE_ADMIN);
        userService.update(userEntity1);
        UserEntity tmp = userService.getByLogin(userEntity.getUsername());

        Assert.assertEquals("ROLE_ADMIN", tmp.getRole());
    }

    @Test
    public void testUnpaidUser(){

    }

}
