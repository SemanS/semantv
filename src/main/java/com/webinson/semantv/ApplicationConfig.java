package com.webinson.semantv;


import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Slavo on 13.09.2016.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"com.webinson.semantv, it.ozimov.springboot"})
@EnableJpaRepositories(basePackages = "com.webinson.semantv.dao")
@EntityScan(basePackages = {"com.webinson.semantv.entity"})
public class ApplicationConfig extends SpringBootServletInitializer {

    @ComponentScan(basePackages = "com.webinson.semantv.service", scopedProxy = ScopedProxyMode.INTERFACES)
    static class Services {
    }

    @ComponentScan(basePackages = "com.webinson.semantv.entity", scopedProxy = ScopedProxyMode.DEFAULT)
    static class Entities {
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationConfig.class);
    }

    @Bean
    public FacesServlet facesServlet() {
        return new FacesServlet();
    }

    @Bean
    public FilterRegistrationBean facesUploadFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new FileUploadFilter(), facesServletRegistration());
        registrationBean.setName("PrimeFaces FileUpload Filter");
        registrationBean.addUrlPatterns("");
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST);
        return registrationBean;
    }


    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(facesServlet(), "*.xhtml");
        registration.setName("FacesServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }

    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
                servletContext.setInitParameter("primefaces.THEME", "omega");
                servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
                servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
                servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
                servletContext.setInitParameter("primefaces.UPLOADER", "commons");
            }
        };

    }

}
