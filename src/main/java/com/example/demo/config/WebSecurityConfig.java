package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true) // da se avtorizira baranjeto koga ke vleze vo metodot i da se avtorisizra baranjeto koga ke izleze od metodot
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()//кои страници да ни бидат достапни
                .antMatchers("/", "/books", "/assets/**",  "/api/**").permitAll()//koi stranici na koi url treba da bidat dozvoleni od korisnici
                //.antMatchers("/admin/**").hasRole("ADMIN")//stranici dozvoleni samo za korisnici so uloga administrator
                .anyRequest()
                .authenticated() //site drugi url so ne se navedeni da se pristapat samo ako e avtenticiran korisnikot
                .and()
                .formLogin()
                .loginPage("/login").permitAll() //deka e dozvolena za site ovaa stranica
                .failureUrl("/login?error=BadCredentials") //ako se sluci exception, togas kazuvame na koe url da odi korisnikot
                .defaultSuccessUrl("/books",true)//ako uspee najavata
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)//da se trgne avtentikacijata
                    .invalidateHttpSession(true)//invalidacija na sesijata
                     .deleteCookies("JSESSIONID")//da se izbrise cookito
                    .logoutSuccessUrl("/login") //ako e uspesno da ne odnese na login
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

    }
    //koj provider ke go koristime vo ramki na nasata apliakcija


}
