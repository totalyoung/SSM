package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
// @EnableAspectJAutoProxy
@ComponentScan("com.total2")
public class ApplicationConfig {

	@Bean
	public RedisConnectionFactory redisCF() {
		JedisConnectionFactory cf = new JedisConnectionFactory();
		cf.setHostName("");
		cf.setPassword("");
		cf.setPort(6379);
		return cf;
	}
}
