package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.TestRedisApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TestRedisApplication.class)
public class RedisServiceTest {

	@Autowired
	RedisService redisService;
	
	@Test
	public void stringTemplate() {
		String test = redisService.test();
		System.out.println(test);
	}
	
	@Test
	public void testMap() {
		redisService.testMap();
		
	}
	
	@Test
	public void testSet() {
		redisService.testSet();
	}
}
