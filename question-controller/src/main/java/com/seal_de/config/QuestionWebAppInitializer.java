package com.seal_de.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class QuestionWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ RootConfig.class };
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebConfig.class };
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /** 添加字符集过滤器 **/
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addFilter("characterEncodingFilter",
                new CharacterEncodingFilter("utf-8", true))
                .addMappingForUrlPatterns(null, false, "/*");
    }

    /** 文件上传属性配置，路径，文件大小，单个请求大小 **/
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(
                new MultipartConfigElement("", 2097152, 4194304, 0));
    }
}
