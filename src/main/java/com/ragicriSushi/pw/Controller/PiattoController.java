package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.PiattoDTO;
import com.ragicriSushi.pw.Service.PiattoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "/piatto",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PiattoController {

    @Autowired
    private PiattoService piattoService;

    @GetMapping(path = "")
    @ApiOperation("Ritorna tutti i piatti")
    public ResponseEntity<Object> getAllUtente(){
        return ResponseEntity.ok(piattoService.getAll());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Ritorna il piatto con l'id inserito")
    public ResponseEntity<Object> getById(@PathVariable int id){
        PiattoDTO dto = piattoService.getById(id);

        if (dto == null){
            return ResponseEntity.notFound().build();
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Piatto non trovato.");
            // funziona ma ritorna un risultato raw, da quindi convertire in JSON, gl&hf
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "/tipologia/{tipologia}")
    @ApiOperation("Ritorna tutti i piatti di una data tipologia")
    public ResponseEntity<Object> getByTipologia(@PathVariable String tipologia){
        List<PiattoDTO> dto = piattoService.getByTipologia(tipologia);
        if (dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

}
