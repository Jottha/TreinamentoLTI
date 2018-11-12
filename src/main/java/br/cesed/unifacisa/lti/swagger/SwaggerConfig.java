package br.cesed.unifacisa.lti.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * O Swagger é um projeto composto por algumas ferramentas que auxiliam o
 * desenvolvedor de APIs REST em alguns tarefas como: Modelagem da API,
 * Geração de documentação (legível) da API e Geração de códigos Cliente e do
 * Sevidor, com suporte a várias linguagens de programação.
 * @author Jack
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postApi() {	
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("public-api")
				.apiInfo(apiInfo())
				.select()
				.paths(postPaths())
				.build();
	}
	
	
	private Predicate<String> postPaths(){
		return (regex("/api.*"));
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("JavaInUse API")
				.description("J")
				.termsOfServiceUrl("http://javainuse.com")
				.contact("JAILTON VENTURA/(Jack) - jottha_2006@hotmail.com")
				.license("Lincença")
				.licenseUrl("java@java.com")
				.version("1.0")
				.build();
	}
	
	
	
	
	
	
	
	
/*	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
                .apis(RequestHandlerSelectors.basePackage("br.cesed.unifacisa.lti"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
        		"Spring Boot Swagger Example API",
                "Spring Boot Swagger Example API for Youtube",
                "1.0",
                "Terms of Service",
                new Contact("TechPrimers", "https://www.youtube.com/TechPrimers",
                        "techprimerschannel@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", null
        );

        return apiInfo;
    }*/
}