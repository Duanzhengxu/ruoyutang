package com.jt.common.config;
/**
 * 启动加载类
 * tomcat启动时会自动加载 /META-INF/services/javax.servlet.ServletContainerInitializer文件 
 * 并加载文件中定义的类以及类上使用@HandleTypes注解定义的类的子类
 */
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override//官方建议此方法中加载service  dao等对象
	protected Class<?>[] getRootConfigClasses() {
		
		return null;
	}
	//此方法一般用于加载spring mvc 组件
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {AppServletConfig.class};
	}
	//此方法中定义请求的拦截 url
	@Override
	protected String[] getServletMappings() {
		
		return new String [] {"*.do"};
	}

}
