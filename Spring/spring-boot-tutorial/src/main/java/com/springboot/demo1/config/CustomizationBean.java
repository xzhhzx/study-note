package com.springboot.demo1.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomizationBean
        implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory container) {
        container.setContextPath("/myapp2");
//        container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
//        container.addErrorPages(new ErrorPage("/errorHaven"));
    }

    public static void main(String[] args) {
        WebServerFactoryCustomizer func = new WebServerFactoryCustomizer() {
            @Override
            public void customize(WebServerFactory factory) {

            }
        };
    }
}