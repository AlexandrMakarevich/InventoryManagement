package com.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringBootConfiguration extends WebMvcConfigurerAdapter {


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/welcome_page.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
//    }
}
