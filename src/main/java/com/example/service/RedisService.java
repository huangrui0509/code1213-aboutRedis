package com.example.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	public String test() {
		String uuid = UUID.randomUUID().toString();
		stringRedisTemplate.opsForValue().set(uuid, "uuid");
		String string = stringRedisTemplate.opsForValue().get(uuid);
		redisTemplate.opsForValue().set("111", "2222");
		String str = (String)redisTemplate.opsForValue().get("111");
		return str;
	}
	
	
	public void testMap() {
		Map<String,String> map = new HashMap();
		map.put("name", "haungrui");
		map.put("age","22");
		map.put("gender", "1");
		redisTemplate.opsForHash().putAll("user:005", map);
		stringRedisTemplate.opsForHash().putAll("user:004", map);
	}
	
	public void testSet() {
		//add(key,value1,value2,...)
		redisTemplate.opsForSet().add("testSet","c","d","f");
		redisTemplate.opsForSet().members("testSet");
	}
	
	
	
	
}
