package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.Utente.AuthBody;
import com.ragicriSushi.pw.Repository.UtenteRepository;
import com.ragicriSushi.pw.Service.UtenteService;
import com.ragicriSushi.pw.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;
@CrossOrigin("*")

@RestController
@RequestMapping("/auth")
public class AuthController {

//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    UtenteRepository users;
//
//    @Autowired
//    UtenteService utenteService;
//
//
//    JwtTokenProvider jwtTokenProvider= new JwtTokenProvider();
//
//    @SuppressWarnings("rawtypes")
//    @PostMapping("/signin")
//    public ResponseEntity signin(@RequestBody AuthBody data) {
//        try {
//            String username = data.getUsername();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
//            String token = jwtTokenProvider.createToken(username, this.users.findUtenteByUsername(username).get().getRuolo().getName());
//            Map<Object, Object> model = new HashMap<>();
//            model.put("username", username);
//            model.put("token", token);
//            return ok(model);
//        } catch (AuthenticationException e) {
//            return new ResponseEntity<>("Username o password errate", HttpStatus.UNAUTHORIZED);
//        }
//    }
//
//    @SuppressWarnings("rawtypes")
//    @PostMapping("/signup")
//    public ResponseEntity signup(@RequestBody UtenteAddDto utenteAddDto) {
//        try{
//            UtenteDao userExistsUsername = userService.findByUsername(utenteAddDto.getUsername());
//            UtenteDao userExistsEmail = userService.findByEmail(utenteAddDto.getEmail());
//            if (userExistsUsername == null && userExistsEmail == null) {
//                UtenteDao utenteDao = new UtenteDao();
//                utenteDao.setId(UUID.randomUUID().toString());
//                utenteDao.setNome(utenteAddDto.getNome());
//                utenteDao.setCognome(utenteAddDto.getCognome());
//                utenteDao.setEmail(utenteAddDto.getEmail());
//                utenteDao.setUsername(utenteAddDto.getUsername());
//                utenteDao.setPassword(utenteAddDto.getPassword());
//                utenteDao.setIsBanned(false);
//
//                userService.saveUser(utenteDao, "ROLE_USER");
//
//                Map<Object, Object> model = new HashMap<>();
//                model.put("message", "User registered successfully");
//                return ok(model);
//
//            } else {
//                throw new BadCredentialsException("Utente con username e/o email correnti già registrato");
//            }
//
//        } catch (Exception e){
//            return internalServerError().body(e.getMessage());
//        }
//    }

}
