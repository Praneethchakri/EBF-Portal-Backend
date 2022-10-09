package com.ebf.EBF_portal.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ebf.EBF_portal.controller.CompanyController;
import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);
	
	@Bean
	public Docket postsApi() {
		LOGGER.info("calling Swagger Service for api's");
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.ebf.EBF_portal.controller"))
//                .paths(regex("/api/.*"))
				.paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/v1.*"), regex("/api/v2.*"), regex("/api/v1/employees"),regex("/api/v1/employees/*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("EBF User Portal API's").description("EBF API reference for developers")
				.termsOfServiceUrl("http://swagger.com").contact("praneeth.itdev@gmail.com").license("EBF GMBH License")
				.licenseUrl("praneeth.itdev@gmail.com").version("1.0").build();
	}
}
