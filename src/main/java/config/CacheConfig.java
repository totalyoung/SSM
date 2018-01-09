package config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
//启用缓存
@EnableCaching
public class CacheConfig {

	/**
	 * 缓存使用redis
	 * @param redisTemplate
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate){
		return new RedisCacheManager(redisTemplate);
	}
	
	/**
	 * 连接redis
	 * @return
	 */
	@Bean
	public RedisConnectionFactory redisCF() {
		JedisConnectionFactory cf = new JedisConnectionFactory();
		cf.setHostName("192.168.1.248");
		cf.setPassword("gentle888");
		cf.setPort(6379);
		cf.setDatabase(0);
		return cf;
	}
	
	/**
	 * 通过RedisTemplate操作redis
	 * @param cf
	 * @return
	 */
	@Bean(name="redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {  
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();  
        redisTemplate.setConnectionFactory(cf);  
        return redisTemplate;  
    }
	
	/**
	 * 通过RedisTemplate操作redis
	 * @param cf
	 * @return
	 */
	@Bean(name="stringRedisTemplate")
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory cf) {  
		StringRedisTemplate redisTemplate = new StringRedisTemplate();  
        redisTemplate.setConnectionFactory(cf);  
        return redisTemplate;  
    }
}
