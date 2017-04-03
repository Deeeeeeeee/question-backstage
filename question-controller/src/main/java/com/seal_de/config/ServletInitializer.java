package com.seal_de.config;

import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * servlet、filter 等配置
 */
public class ServletInitializer implements WebApplicationInitializer{
    public void onStartup(ServletContext servletContext) throws ServletException {
        Dynamic customFilter = servletContext.addFilter("SpringOpenSessionInViewFilter",
                OpenSessionInViewFilter.class);
        customFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
