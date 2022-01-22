package com.ragicriSushi.pw.Controller;


import com.ragicriSushi.pw.DTO.Role.NameRuoloDTO;
import com.ragicriSushi.pw.DTO.Role.RoleAddDto;
import com.ragicriSushi.pw.DTO.Role.RoleDTO;
import com.ragicriSushi.pw.Service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(
        path = "/role",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(path = "")
    @ApiOperation("Ritorna tutti i ruoli")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @PostMapping(path = "/add")
    @ApiOperation("Aggiungi un ruolo")
    public ResponseEntity<Object> add(@RequestBody RoleAddDto dto) {
        RoleDTO result = roleService.addRuolo(dto);
        if (result == null) {
            return ResponseEntity.badRequest().body("{\"status\": \"Ruolo già esistente.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
    }

    @PostMapping(path = "/delete")
    @ApiOperation("Elimina il ruolo")
    public ResponseEntity<Object> delete(@RequestBody NameRuoloDTO dto) {
        RoleDTO result = roleService.delete(dto);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Ruolo non trovato.\"}");
        }
    }
}
