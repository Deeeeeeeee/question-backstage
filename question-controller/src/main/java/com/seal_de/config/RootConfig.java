package com.seal_de.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/** ContextLoaderListener 创建上下文，加载驱动应用后端中的中间层和数据层组件的 bean **/
@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = {"com.seal_de"},
        excludeFilters={@Filter(type= FilterType.ANNOTATION, value= EnableWebMvc.class)})
public class RootConfig {
}
