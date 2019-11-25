package com.itsight.signbox.component;

import com.itsight.signbox.domain.dto.SecurityUserDTO;
import com.itsight.signbox.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.itsight.util.Enums.Galletas.GLL_NOMBRE_COMPLETO;
import static com.itsight.util.Utilitarios.createCookie;


@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired(required = false)
    private HttpSession session;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent login) {
        // TODO Auto-generated method stub
        try {
            //For cookies
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

            String[] usernameAndId = StringUtils.split(login.getAuthentication().getName(), "|");

            String username = usernameAndId[0];
            Integer id = Integer.parseInt(usernameAndId[1]);
            SecurityUserDTO currentUser;
            session.setAttribute("id", id);

            currentUser = usuarioService.getForCookieById(id);


            //Fixing authentication object
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, login.getAuthentication().getCredentials(), SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Generando cookies
            if(currentUser != null){
                String fullName = currentUser.getNombres() + " " + currentUser.getApellidos();
                response.addCookie(createCookie(GLL_NOMBRE_COMPLETO.name(), new String(Base64.getEncoder().encode(fullName.getBytes()))));

            }

            Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();

            Set<String> roles = ((org.springframework.security.core.Authentication) authentication).getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toSet());


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
