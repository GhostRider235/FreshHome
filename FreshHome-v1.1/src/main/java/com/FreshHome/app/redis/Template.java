package com.FreshHome.app.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class Template {

    private final RedisTemplate<String, Object> redis;

	public Template(RedisTemplate<String, Object> redis) {
		super();
		this.redis = redis;
	}

	
    
	
}
