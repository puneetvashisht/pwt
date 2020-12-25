package com.sprint2.personalworkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@SpringBootApplication
@ComponentScan
@EnableSwagger2
public class PersonalworkoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalworkoutApplication.class, args);
	}
	
	@Bean
	public Docket swaggerBeanDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.sprint2"))
				.build();
	}
	
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("PERSONAL WORKOUT TRACKER API")
				.description("This API is used to CRUD operations of User")
				.build();
	}
	
	
}
