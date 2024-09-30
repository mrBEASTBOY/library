package com.selfproject.librarybe.config;

import com.selfproject.librarybe.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyAppConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedMethods = { HttpMethod.PATCH, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE };
        disableHttpMethod(config, Book.class, theUnsupportedMethods);

    }

    private void disableHttpMethod(RepositoryRestConfiguration config, Class theClass, HttpMethod[] theUnsupportedMethods) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedMethods));
    }
}
