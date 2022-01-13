package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.PiattoDTO;
import com.ragicriSushi.pw.DTO.UpdatePiattoDTO;
import com.ragicriSushi.pw.DTO.UtenteDTO;
import com.ragicriSushi.pw.Service.UtenteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "")
    @ApiOperation("Ritorna tutti gli utenti")
    public ResponseEntity<Object> getAllUtente(){
        return ResponseEntity.ok(utenteService.getAll());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Ritorna un utente con l'id inserito")
    public ResponseEntity<Object> getById(@PathVariable int id){
        UtenteDTO dto = utenteService.getById(id);

        if (dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "/ruolo/{ruolo}")
    @ApiOperation("Ritorna tutti gli utenti con un ruolo")
    public ResponseEntity<Object> getByRuolo(@PathVariable String ruolo){
        List<UtenteDTO> dto = utenteService.getByRuolo(ruolo);
        if (dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @DeleteMapping(path = "/delete")
    @ApiOperation("Elimina l'utente")
    public ResponseEntity<Object> delete(@RequestBody String idStr){
        try{
            int id = Integer.parseInt(idStr);
            UtenteDTO dto = utenteService.delete(id);
            if (dto != null){
                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e)
        {
            return ResponseEntity.badRequest().body("L'id " + idStr + " non è valido.");
        }
    }

    @PostMapping("/save")
    @ApiOperation("Crea un utente")
    public UtenteDTO save(@RequestBody UtenteDTO dto) {
        return utenteService.save(dto);
    }

}
