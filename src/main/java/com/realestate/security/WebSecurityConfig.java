package com.realestate.security;

import com.realestate.security.jwt.AuthTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor                     //WebSecurityCustomizer
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//TODO kemal beye soralim

    private UserDetailsService userDetailsService;

    //TODO  csrf soralim neden disable ediliyor
    @Override//CORS ayarlari yapilmazsa disardadn api ye baglanmiyoor
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                authorizeRequests().antMatchers("/login","/register").permitAll().
                anyRequest().authenticated();

          http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    
    @Bean// AuthTokenFilter(); classsin dan bu methodu filter olarak kullan
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //sana password verecem sen bunu bcrypt ile encode et
    }

}
