package com.webinson.semantv;

import java.util.Arrays;

import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService())
                .formLogin()
                .loginPage("/login.xhtml")
                .loginProcessingUrl("/appLogin")
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/dashboard").and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/upload").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/dashboard").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/dashboard.xhtml").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/dashboard/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/fileUpload.xhtml").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/city/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/templates/**").permitAll()
                .anyRequest().permitAll();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = new User("ing.vladimir.seman", "vsx10058", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
        //UserDetails user2 = new User("nyilmaz", "qwe", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        return new InMemoryUserDetailsManager(Arrays.asList(user1));
    }
}
