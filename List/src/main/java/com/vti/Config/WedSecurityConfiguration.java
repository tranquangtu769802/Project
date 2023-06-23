package com.vti.Config;

import com.vti.Config.Exception.AuthExceptionHandler;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WedSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private AuthExceptionHandler authExceptionHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // wedSecurity: khong can phan quyen ma van truy cap duoc
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/api/v1/accounts/sendMail", "/api/v1/accounts/updatePassword");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authExceptionHandler)
                .accessDeniedHandler(authExceptionHandler)
                .and()
                .authorizeRequests()
//                .antMatchers("/api/v1/departments/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers("/**").permitAll()//ai cũng có thế truy cập vào đường link này
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}