package com.example.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@PropertySource("classpath:redis.properties")
public class RedisCacheConfiguration extends CachingConfigurerSupport{

	Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;
	
	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;
	
	@Value("${spring.redis.password}")
	private String password;
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		System.out.println("------------------");
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		redisConnectionFactory.setHostName(host);
		redisConnectionFactory.setPort(port);
		redisConnectionFactory.setPoolConfig(jedisPoolConfig);
		//redisConnectionFactory.setPassword(password);
		return redisConnectionFactory;
	
	}
	@Bean
	public StringRedisTemplate  stringRedisTemplate() {
		StringRedisTemplate  stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
		stringRedisTemplate.afterPropertiesSet();
		return stringRedisTemplate;
	}
	
	@Bean
	public RedisTemplate<String,Object> redisTemplate(){
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	
	@Bean
	public CacheManager cacheManager() {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
		cacheManager.setDefaultExpiration(timeout);
		cacheManager.setLoadRemoteCachesOnStartup(true);
		cacheManager.setUsePrefix(true);
		//cacheManager.setCachePrefix(new DefaultRedisCachePrefix("prefix"));
		return cacheManager;		
	}
	
	
	
}
