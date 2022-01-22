package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.Utente.IndirizzoDTO;
import com.ragicriSushi.pw.Service.IndirizzoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        path = "/indirizzo",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping(path = "")
    @ApiOperation("Ritorna tutti gli indirizzi")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(indirizzoService.getAll());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Ritorna un indirizzo con l'id inserito")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        IndirizzoDTO dto = indirizzoService.getById(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @PostMapping(path = "/update")
    @ApiOperation("Aggiorna un indirizzo")
    public ResponseEntity<Object> update(@RequestBody IndirizzoDTO dto){
        IndirizzoDTO result = indirizzoService.update(dto);
        if (dto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": \"Indirizzo non trovato.\"}");
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }
}
