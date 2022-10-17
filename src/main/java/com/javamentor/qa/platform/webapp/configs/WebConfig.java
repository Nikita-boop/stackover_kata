package com.javamentor.qa.platform.webapp.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/question").setViewName("question");
        registry.addViewController("/main").setViewName("mainpage");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/tagpage").setViewName("tagpage");
        registry.addViewController("/regpage").setViewName("regpage");
    }
}
