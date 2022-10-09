package com.ebf.EBF_portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("development")
public class CorsConfiguration implements WebMvcConfigurer{
	
public void addCorsMappints(CorsRegistry registry) {
	registry.addMapping("/api/**").allowedMethods("GET","POST","PUT","DELETE");
}

}
