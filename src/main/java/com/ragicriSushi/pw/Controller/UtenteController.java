package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.EmailDTO;
import com.ragicriSushi.pw.DTO.Utente.*;
import com.ragicriSushi.pw.DTO.NumeroDTO;
import com.ragicriSushi.pw.Service.MailService;
import com.ragicriSushi.pw.Service.RoleService;
import com.ragicriSushi.pw.Service.UtenteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/utente",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MailService mailService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "")
    @ApiOperation("Ritorna tutti gli utenti")
    public ResponseEntity<Object> getAllUtente() {
        return ResponseEntity.ok(utenteService.getAll());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/utentiAzienda")
    @ApiOperation("Ritorna tutti i dipendenti e amministratori")
    public ResponseEntity<Object> getAllUtentiAzienda() {
        return ResponseEntity.ok(utenteService.getAllUtentiAzienda());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/{id}")
    @ApiOperation("Ritorna un utente con l'id inserito")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        UtenteDTO dto = utenteService.getById(id);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/username/{username}")
    @ApiOperation("Ritorna un utente con lo username inserito")
    public ResponseEntity<Object> getByUsername(@PathVariable String username) {
        UtenteDTO dto = utenteService.getByUsername(username);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @PreAuthorize("hasRole('ROLE_UTENTE')")
    @GetMapping(path = "/email/{username}")
    @ApiOperation("Ritorna un utente con lo username inserito")
    public ResponseEntity<Object> getEmail(@PathVariable String username) {
        EmailDTO dto = utenteService.getEmail(username);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/ruolo/{ruolo}")
    @ApiOperation("Ritorna tutti gli utenti con un ruolo")
    public ResponseEntity<Object> getByRuolo(@PathVariable String ruolo) {
        List<UtenteDTO> dto = utenteService.getByRuolo(ruolo);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/indirizzo/{username}")
    @ApiOperation("Ritorna l'indirizzo dell'utente con lo username specificato")
    public ResponseEntity<Object> getIndirizzoByUsername(@PathVariable String username) {
        IndirizzoDTO result = utenteService.getIndirizzoByUsername(username);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PreAuthorize("hasRole('ROLE_UTENTE')")
    @GetMapping(path = "/IdIndirizzo/{username}")
    @ApiOperation("Ritorna l'id dell'indirizzo dell'utente con lo username specificato")
    public ResponseEntity<Object> getIdIndirizzoByUsername(@PathVariable String username) {
        NumeroDTO result = utenteService.getIdIndirizzoByUsername(username);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/delete")
    @ApiOperation("Elimina l'utente")
    public ResponseEntity<Object> delete(@RequestBody NumeroDTO id) {
        try {
            UtenteDTO dto = utenteService.delete(id.getNumero());
            if (dto != null) {
                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("L'id " + id + " non è valido.");
        }
    }

    @PostMapping("/add")
    @ApiOperation("Crea un utente")
    public ResponseEntity<Object> add(@RequestBody AddUtenteDTO dto) {
        if (utenteService.checkPresenzaUsername(dto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": \"Username già in uso.\"}");
        } else if (roleService.checkByName(dto.getRuolo())) {
            UtenteDTO result = utenteService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": \"Ruolo non esistente.\"}");
    }

    @PreAuthorize("hasRole('ROLE_UTENTE')")
    @PostMapping(path = "/update")
    @ApiOperation("Aggiorna un utente")
    public ResponseEntity<Object> update(@RequestBody UpdateUtenteDto dto) {
        UtenteDTO result = utenteService.update(dto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PreAuthorize("hasRole('ROLE_UTENTE')")
    @PostMapping(path = "/checkPassword")
    @ApiOperation("verifica password")
    public ResponseEntity<Object> checkPasswword(@RequestBody UtenteModificaPassword dto) {

        UtenteDTO utente = utenteService.getById(dto.getId());
        if (utente!=null) {
            Boolean result = utenteService.checkPassword(utente.getPassword(),dto.getPassword());
            if (result) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": \"Password non corrispondente.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"Utente non trovato.\"}");
        }
    }

    @GetMapping(path = "/inviaMail/{email}")
    @ApiOperation("Invia Mail")
    public ResponseEntity<Object> inviaMail(@PathVariable String email) {
        return ResponseEntity.ok(mailService.sendEmail(email,2));
    }
}
