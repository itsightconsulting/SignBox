package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.domain.dto.SecurityUserDTO;
import com.itsight.signbox.repository.PersonaRepository;
import com.itsight.signbox.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class ClientSecurityServiceImpl implements UserDetailsService {

    PersonaRepository personaRepository;
    UsuarioRepository usuarioRepository;


    public ClientSecurityServiceImpl(PersonaRepository personaRepository , UsuarioRepository usuarioRepository) {
        this.personaRepository = personaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      //  String comodin = username.split();
        Set<GrantedAuthority> lstRol ;
        // TODO Auto-generated method stub
       Persona personaCliente = personaRepository.findByCorreo(username);

        if (personaCliente != null) {
            return buildUser(personaCliente, buildAuthorities(
                  "ROLE_CLIENT"));
        }
     //   LOGGER.info("> UsernameException | (?): " + username.toUpperCase());
        throw new UsernameNotFoundException("UsernameNotFoundException | (?): " + username.toUpperCase());
    }

    private User buildUser(Persona personaCliente, Set<GrantedAuthority> lstRole) {
        return
                new User(personaCliente.getCorreo() + "|"+personaCliente.getPersonaId(),//Artificio
                        personaCliente.getHashContrasenia(),
                        personaCliente.isFlagActivo(),
                        true,
                        true,
                        personaCliente.isFlagActivo(), lstRole);
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
