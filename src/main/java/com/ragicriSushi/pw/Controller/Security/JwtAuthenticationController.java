package com.ragicriSushi.pw.Controller.Security;


import java.util.Optional;

import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.Repository.UtenteRepository;
import com.ragicriSushi.pw.security.JwtTokenUtil;
import com.ragicriSushi.pw.security.JwtUserDetailsService;
import com.ragicriSushi.pw.security.model.JwtRequest;
import com.ragicriSushi.pw.security.model.JwtResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UtenteRepository utenteRepository;

    @PostMapping(path = "/authenticate")
    @ApiOperation("Autenticazione")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        Optional<UtenteDAO> utente=utenteRepository.findUtenteByUsername(authenticationRequest.getUsername());
        if(!utente.isPresent())
        {
            throw new UsernameNotFoundException("Utente non trovato");
        }

        final String token = jwtTokenUtil.generateToken(utente);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
