package com.example.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.domain.User;

@Service
public class UserService {

	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	
	public void setUserToRedis(User user) {
		
		redisTemplate.opsForSet().add("user:007", user);
		
	}
	
	public Set<User> getUserFromRedis() {
		Set<User> members = redisTemplate.opsForSet().members("user:007");
		return members;
	}
	
}
