package com.prueba.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendPruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPruebaApplication.class, args);
	}

	@Configuration
	public static class Myconfiguration{
		@Bean
		public WebMvcConfigurer corsConfigurer(){
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
				
						registry.addMapping("/**")
				                .allowedOrigins("https://frontend-prueba-wine.vercel.app")
				                .allowedMethods("GET", "POST", "PUT", "DELETE")
				                .allowedHeaders("Content-Type", "Authorization")
				                .allowCredentials(true);
							
				}
			};
		}
	}
}
