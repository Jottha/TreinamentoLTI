package br.cesed.unifacisa.lti.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
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
	/**
	 * 
	 * @return
	 */
	@Bean
	public Docket postsApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.cesed.unifacisa.lti.controller"))
				.paths(regex("/v1.*"))
				.build();
//				.groupName("public-api")
//				.apiInfo(apiInfo()).select()
//				.paths(postPaths())
//				.build();
	}
	/**
	 * 
	 * @return
	 */
	/*private Predicate<String> postPaths(){
		return or (regex("/api/posts.*"), regex("/api.*"));
	}*/
	/**
	 *O método apiInfo() é responsável definição das informações iniciais, como:
	 *Titulo, Descrição, Contato e Versão. 
	 * @return ApiInfoBuilder retorna uma nova instância, que constroi a Api com
	 * todas as informações.
	 */
	/*@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("PORTFOLIO - Treinamento/LTI")
				.description("Api projeto")
				.contact("Jack")
				.version("1.0")
				.build();
	}*/
}