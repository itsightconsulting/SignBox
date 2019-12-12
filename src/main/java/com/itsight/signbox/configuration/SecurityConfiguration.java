package com.itsight.signbox.configuration;

import com.itsight.signbox.component.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter   {

    @Autowired
    @Qualifier("securityServiceImpl")
    private UserDetailsService securityService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(securityService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder encoder() {
            return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/configuracion/**/").hasRole("ADMINISTRATOR")
                .antMatchers("/seguridad/credenciales/**").hasAnyRole("ADMINISTRATOR", "OPERATOR")
                .antMatchers("/seguridad/**").hasRole("ADMINISTRATOR")
                .antMatchers("/reportes/**/").hasAnyRole("ADMINISTRATOR", "AUDITOR")
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/fonts/**",
                        "/sound/**",
                        "/app/**",
                        "/login/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("nombreUsuario")
                .passwordParameter("contrasena")
                .loginProcessingUrl("/postLogin")
                .defaultSuccessUrl("/default")
                .failureHandler(customAuthenticationFailureHandler())
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .deleteCookies("SESSION")
                .and().csrf().disable();

    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
            return new CustomAuthenticationFailureHandler();
    }




}

