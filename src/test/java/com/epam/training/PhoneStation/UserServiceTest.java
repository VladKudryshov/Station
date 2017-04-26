package com.epam.training.PhoneStation;


import com.epam.training.PhoneStation.model.Role;
import com.epam.training.PhoneStation.model.User;
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
public class UserServiceTest {
    @Autowired
    private UserService userService;

    public User user= new User();


    @Before
    public void setup(){
        user.setUsername("sash1111112a");
        user.setPassword("123");
        user.setRole(Role.ROLE_USER);
    }

    @Test
    public void testAddUser()
    {
        user = userService.addUser(user);

        User tmp = userService.getById(user.getId());
        Assert.assertEquals(user.getId(), tmp.getId());
    }

    @Test
    public void testGetUser(){
        User user1 = userService.getByLogin(user.getUsername());
        Assert.assertNotNull(user1);
    }

    @Test
    public void testDeleteUser(){
        user = userService.getByLogin(user.getUsername());
        userService.delete(user.getId());
        user = userService.getByLogin(user.getUsername());

        Assert.assertNull(user);
    }
    @Test
    public void testUpdateUser(){
        User user1 = userService.getByLogin(user.getUsername());
        user1.setRole(Role.ROLE_ADMIN);

        userService.update(user1);

        User tmp = userService.getByLogin(user.getUsername());
        Assert.assertEquals("USERBLOCKED", tmp.getRole());
    }

}
