package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.dto.SecurityUserDTO;
import com.itsight.signbox.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class SecurityServiceImpl implements UserDetailsService {

    UsuarioRepository usuarioRepository;

    public SecurityServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      //  String comodin = username.split();
        // TODO Auto-generated method stub
       SecurityUserDTO user = usuarioRepository.findByNombreUsuario(username);
        if (user != null) {
            return buildUser(user, buildAuthorities(
                    user.getRol()));
        }
     //   LOGGER.info("> UsernameException | (?): " + username.toUpperCase());
        throw new UsernameNotFoundException("UsernameNotFoundException | (?): " + username.toUpperCase());
    }

    private User buildUser(SecurityUserDTO securityUser, Set<GrantedAuthority> lstRole) {
        return
                new User(securityUser.getNombreUsuario() + "|"+securityUser.getUsuarioId(),//Artificio
                        securityUser.getContrasena(),
                        securityUser.isFlagActivo(),
                        true,
                        true,
                        securityUser.isFlagActivo(), lstRole);
    }

    private Set<GrantedAuthority> buildAuthorities(String roles) {
        String[] arrRoles = roles.split("\\|");
        HashSet<String> flRoles = new HashSet<>(Arrays.asList(arrRoles));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String role: flRoles) {
        //    LOGGER.debug("> USER ROLE: " + role);
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return grantedAuthorities;
    }
}
