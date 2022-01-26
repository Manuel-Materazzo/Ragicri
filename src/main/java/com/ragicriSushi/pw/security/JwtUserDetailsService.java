package com.ragicriSushi.pw.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.Repository.RoleRepository;
import com.ragicriSushi.pw.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UtenteDAO> utente=utenteRepository.findUtenteByUsername(username);
        if(!utente.isPresent())
        {
            throw new UsernameNotFoundException("Utente non trovato");
        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        roleRepository.findAll().forEach(ruolo ->{ authorities.add(new SimpleGrantedAuthority(
                ruolo.getName()));
        });
        return new org.springframework.security.core.userdetails.User(utente.get().getUsername(),
                utente.get().getPassword(),authorities);
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("javainuse".equals(username)) {
//            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//    }
}
