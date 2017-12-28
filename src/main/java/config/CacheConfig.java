package config;

import javax.servlet.RequestDispatcher;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CacheConfig {

//	public CacheManager cacheManager(RedisTemplate<K, V>){
//		return new RedisC
//	}
}
