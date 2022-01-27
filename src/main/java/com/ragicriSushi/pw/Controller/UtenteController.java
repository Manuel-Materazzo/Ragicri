package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.Utente.AddUtenteDTO;
import com.ragicriSushi.pw.DTO.NumeroDTO;
import com.ragicriSushi.pw.DTO.Utente.UtenteDTO;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(
        path = "/utente",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "")
    @ApiOperation("Ritorna tutti gli utenti")
    public ResponseEntity<Object> getAllUtente() {
        return ResponseEntity.ok(utenteService.getAll());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Ritorna un utente con l'id inserito")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        UtenteDTO dto = utenteService.getById(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "/ruolo/{ruolo}")
    @ApiOperation("Ritorna tutti gli utenti con un ruolo")
    public ResponseEntity<Object> getByRuolo(@PathVariable String ruolo) {
        List<UtenteDTO> dto = utenteService.getByRuolo(ruolo);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
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
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": \"Utente non trovato.\"}");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("L'id " + id + " non è valido.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    @ApiOperation("Crea un utente")
    public ResponseEntity<Object> add(@RequestBody AddUtenteDTO dto) {
        if (utenteService.checkPresenzaUsername(dto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": \"Username già in uso.\"}");
        } else if (roleService.checkByName(dto.getRuolo().getName())) {
            UtenteDTO result = utenteService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": \"Ruolo non esistente.\"}");
    }

    @PostMapping(path = "/update")
    @ApiOperation("Aggiorna un utente")
    public ResponseEntity<Object> update(@RequestBody UtenteDTO dto) {
        UtenteDTO result = utenteService.update(dto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": \"Utente non trovato.\"}");
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
