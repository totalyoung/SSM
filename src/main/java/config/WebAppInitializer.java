package config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	//返回的带有@Configuration注解的类将会用来配置ContextLoaderListener创建的应用上下文中的bean
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ApplicationConfig.class};
	}

	//返回的带有@Configuration注解的类将会用来定义DispatcherServlet应用上下文中的bean。
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{MVCConfig.class};
	}

	
	//它会将一个或多个路径映射到DispatcherServlet上。
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
//	@Override
//	protected void customizeRegistration(Dynamic registration){
//		registration.setInitParameters(arg0)
//	}

}
