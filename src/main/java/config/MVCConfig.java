package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.common.Aspect2;
import com.common.AspectLog;

@Configuration
@EnableAspectJAutoProxy // 启用切面，范围在@ComponentScan扫描
@EnableWebMvc // 启用mvc，范围在@ComponentScan扫描
@ComponentScan("com.total") // 启用组件扫描
public class MVCConfig extends WebMvcConfigurerAdapter {

	private int a = 1;

	/**
	 * 配置视图解析器
	 * 
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/view/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);

		// CharacterEncodingFilter f = new CharacterEncodingFilter();
		// Dynamic d = new;
		// FilterConfig fc = f.getFilterConfig();

		return resolver;
	}

	// 配置静态资源处理
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public AspectLog aspectLog() {
		return new AspectLog();
	}

	@Bean
	public Aspect2 aspect2() {
		return new Aspect2();
	}

}
