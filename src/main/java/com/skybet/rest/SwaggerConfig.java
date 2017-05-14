package com.skybet.rest;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.skybet.rest"))
                .paths(regex("/v1.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){

        ApiInfo apiInfo = new ApiInfo( "Bets Rest Api"
                                     , "Rest Api for Converting from Fractions to Decimal "
                                     , "v1.0.0"
                                     , "TOS"
                                     ,new Contact("Jitin","","kjitin@gmail.com")
                                     ,"","");

        return apiInfo;

    }
}
