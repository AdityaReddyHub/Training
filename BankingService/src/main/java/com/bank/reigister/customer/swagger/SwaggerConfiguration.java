package com.bank.reigister.customer.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

 @Bean
public Docket api() {

	 
	 //return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	 
	 return new Docket(DocumentationType.SWAGGER_2).select()
			 .apis(RequestHandlerSelectors.any())
			 .paths(PathSelectors.any()).build()
			 .apiInfo(getapiInfo());
	
}
 
 @SuppressWarnings("deprecation")
private ApiInfo getapiInfo() {
	 return new ApiInfo("BankingService Api", "Customer Registration and Transaction Process", "1.0","","Aditya Reddy","Apache License 2.0", "");
	 
 }

}
