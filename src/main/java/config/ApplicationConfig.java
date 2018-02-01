package config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.common.FileUtil;

@Configuration
// @EnableAspectJAutoProxy
@ComponentScan("com.application")

public class ApplicationConfig {

//	@Bean
//	public PropertiesFactoryBean propertiesFactoryBean(){
//		PropertiesFactoryBean propertiesBean = new PropertiesFactoryBean();
//		propertiesBean.setLocations(FileUtil.getProperties());
//		return propertiesBean;
//	}
	
	@Bean
	public PropertyPlaceholderConfigurer  propertyPlaceholderConfigurer(){
		PropertyPlaceholderConfigurer p = new PropertyPlaceholderConfigurer();
		p.setLocations(FileUtil.getProperties());
		p.setIgnoreResourceNotFound(true);
		return p;
	}
}
