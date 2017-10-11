package com.brotherhood.stocktaking.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.brotherhood.stocktaking")
@EnableSwagger2
@Configuration
public class StocktakingConfiguration {
    private static final String API_VERSION = "1.0";
    private static final String SWAGGER_TITLE = "Stocktaking API";
    private static final String SWAGGER_DESCRIPTION = "Server API for clients!";
    private static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String DATABASE_MODELS_PACKAGE = "com.brotherhood.stocktaking.*";

    public static void main(String[] args) {
        SpringApplication.run(StocktakingConfiguration.class, args);
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username("root")
                .url("jdbc:mysql://localhost:3306/webapp")
                .driverClassName(JDBC_DRIVER_CLASS_NAME)
                .build();
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(DATABASE_MODELS_PACKAGE);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getSwaggerInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getResponseMessages())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.brotherhood.stocktaking.controllers"))
                .paths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return Predicates.not(PathSelectors.regex("/basic-error-controller.*"));
    }

    private ApiInfo getSwaggerInfo() {
        return new ApiInfoBuilder()
                .title(SWAGGER_TITLE)
                .description(SWAGGER_DESCRIPTION)
                .version(API_VERSION)
                .build();
    }

    private List<ResponseMessage> getResponseMessages() {
        ArrayList<ResponseMessage> result = new ArrayList<>();
        result.add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal server error")
                .responseModel(new ModelRef("error"))
                .build());
        result.add(new ResponseMessageBuilder()
                .code(200)
                .message("Success")
                .responseModel(new ModelRef("success"))
                .build());
        result.add(new ResponseMessageBuilder()
                .code(401)
                .message("Unauthorized")
                .responseModel(new ModelRef("unathorized"))
                .build());
        return result;
    }
}
