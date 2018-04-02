package ru.springboot.app.library.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers()
                    .permitAll()
                    .antMatchers("/addLibrarian", "/deleteLibrarian/**").hasRole("ADMIN")
                    .antMatchers("/addReader", "/deleteReader/**", "/showReaders").hasAnyRole("ADMIN", "LIBRARIAN")
                    .anyRequest()
                    .authenticated();
        http
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Configuration
    protected static class AuthenticationConfiguration extends
            GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("admin").password("{noop}admin").roles("ADMIN")
                    .and()
                    .withUser("librarian").password("{noop}librarian").roles("LIBRARIAN")
                    .and()
                    .withUser("user").password("{noop}user").roles("USER");
        }

    }
}
