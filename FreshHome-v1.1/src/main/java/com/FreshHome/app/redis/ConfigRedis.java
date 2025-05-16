package com.FreshHome.app.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ConfigRedis {
	
	
	@Bean
	public RedisTemplate<String, Object> templateRedis(RedisConnectionFactory factory){
		RedisTemplate<String, Object> redis = new RedisTemplate<>();
		redis.setConnectionFactory(factory);
		
		//Serializacion
		redis.setKeySerializer(new StringRedisSerializer());
		redis.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return redis;
	}
}
