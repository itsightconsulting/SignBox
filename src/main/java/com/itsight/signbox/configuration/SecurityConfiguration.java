package com.itsight.signbox.configuration;

import com.itsight.signbox.component.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
public class SecurityConfiguration  {


        @Configuration
        @Order(1)
        public static class DefaultConfigurationAdapter extends WebSecurityConfigurerAdapter {

                @Autowired
                @Qualifier("securityServiceImpl")
                private UserDetailsService securityService;

            public DefaultConfigurationAdapter() {
                super();
            }

            /*
                                    @Override
                                    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                                            auth.inMemoryAuthentication()
                                                    .withUser("user")
                                                    .password(encoder().encode("password"))
                                                    .roles("ADMINISTRATOR");
                                    }

                            */
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

                        http.antMatcher("/portalAdmin/**").authorizeRequests().anyRequest().hasAnyRole("ADMINISTRATOR")
                            .and()
                                .exceptionHandling()
                                .accessDeniedPage("/403")
                                .and()
                                .formLogin()
                                .loginPage("/portalAdminLogin")
                                .usernameParameter("nombreUsuario")
                                .passwordParameter("contrasena")
                                .loginProcessingUrl("/portalAdmin/postLogin")
                                .defaultSuccessUrl("/portalAdmin/parametros/gestion")
                                .failureUrl("/login?error")
                                .and().logout().logoutUrl("/portalAdmin/logout").logoutSuccessUrl("/amLogoutSuccessful")
                                .deleteCookies("JSESSIONID")
                                .and().csrf().disable();

                }


                @Bean
                public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
                        return new CustomAuthenticationFailureHandler();
                }

        }

        @Configuration
        @Order(2)
        public static class ClientConfigurationAdapter extends WebSecurityConfigurerAdapter {

                @Autowired
                @Qualifier("clientSecurityServiceImpl")
                private UserDetailsService clientSecurityService;



            public ClientConfigurationAdapter() {
                super();
            }
                /*
                        @Override
                        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                                auth.inMemoryAuthentication()
                                        .withUser("user")
                                        .password(encoder().encode("password"))
                                        .roles("ADMINISTRATOR");
                        }

                */
                @Autowired
                public void configure(AuthenticationManagerBuilder auth) throws Exception {
                        auth.userDetailsService(clientSecurityService).passwordEncoder(new BCryptPasswordEncoder());
                }

                @Bean
                public PasswordEncoder encoderClient() {
                        return new BCryptPasswordEncoder();
                }

                @Override
                protected void configure(HttpSecurity http) throws Exception {

                        http
                                //.authorizeRequests().antMatchers("/am/**").access("hasRole('ROLE_AM')")
                                .antMatcher("/cliente/**").authorizeRequests().anyRequest().hasRole("CLIENT")
                                .and()
                                .exceptionHandling()
                                .accessDeniedPage("/403")
                                .and()
                                .formLogin()
                                .loginPage("/clienteLogin")
                                .usernameParameter("nombreUsuario")
                                .passwordParameter("contrasena")
                                .loginProcessingUrl("/cliente/postLogin")
                                .defaultSuccessUrl("/cliente/index")
                                .failureUrl("/login?error")
                                .and().logout().logoutUrl("/cliente/logout").logoutSuccessUrl("/amLogoutSuccessful")
                                .deleteCookies("JSESSIONID")
                                .and().csrf().disable();
                }

                @Bean
                public CustomAuthenticationFailureHandler customClientAuthenticationFailureHandler() {
                        return new CustomAuthenticationFailureHandler();
                }

        }


}

