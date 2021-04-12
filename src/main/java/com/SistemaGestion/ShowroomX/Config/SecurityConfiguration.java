package com.SistemaGestion.ShowroomX.Config;

import com.SistemaGestion.ShowroomX.Repository.JWTService;
import com.SistemaGestion.ShowroomX.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserServiceImpl userDetailsService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    public SecurityConfiguration(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/provider/**").hasRole("ADMIN")
                .antMatchers("/purchase/**").hasRole("ADMIN")
                .antMatchers("/client/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/brand/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/sales/**").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
                /*.and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()*/
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

     /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("msimon")
                .password(this.bCryptPasswordEncoder().encode("simon"))
                .roles("ADMIN")
                .build();

        UserDetails userDetails1 = User.builder()
                .username("msola")
                .password(this.bCryptPasswordEncoder().encode("simon"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails, userDetails1);
    }*/

}
