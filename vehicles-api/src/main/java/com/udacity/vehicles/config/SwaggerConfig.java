package com.udacity.vehicles.config;

import com.sun.org.apache.bcel.internal.generic.FALOAD;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ApiResponses(value = {
        @ApiResponse(code=400, message = "something went wrong"),
        @ApiResponse(code = 500, message = "The server is down"),
        @ApiResponse(code = 401, message = "unauthorized to perform request. Please check your credentials")
})
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select() //controls endpoints exposed by swagger
                .apis(RequestHandlerSelectors.any()) //make doc available for entire project
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }
}
