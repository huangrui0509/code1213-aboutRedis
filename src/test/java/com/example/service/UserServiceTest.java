package com.example.service;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.TestRedisApplication;
import com.example.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TestRedisApplication.class)
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	public void getUser() {
		Set<User> users= userService.getUserFromRedis();
		for (User user : users) {
			System.out.println(user);
		}
		
	}
}
