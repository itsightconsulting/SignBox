package com.itsight.signbox.component;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomClientAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception.getClass().isAssignableFrom(AccountStatusException.class)) {
            response.sendRedirect("/clienteLogin?error=acc-status");
        }
   /*     else if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
            response.sendRedirect("/login?error=username-credential");
        }
     */
        else if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            response.sendRedirect("/clienteLogin?error=true");
        }

        else if (exception.getClass().isAssignableFrom(SessionAuthenticationException.class)) {
            response.sendRedirect("/clienteLogin?error=loggedin");
        }else if(exception.getClass().isAssignableFrom(LockedException.class)){
            response.sendRedirect("/clienteLogin?error=disabled");
        }else{
            response.sendRedirect("/clienteLogin?error=true");
        }


    }
}
