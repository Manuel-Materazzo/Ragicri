package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.GetPiattoDTO;
import com.ragicriSushi.pw.DTO.PiattoDTO;
import com.ragicriSushi.pw.DTO.UpdatePiattoDTO;
import com.ragicriSushi.pw.Service.PiattoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(
        path = "/piatto",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PiattoController {

    @Autowired
    private PiattoService piattoService;

    @GetMapping(path = "")
    @ApiOperation("Ritorna tutti i piatti")
    public ResponseEntity<Object> getAll(){
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

    @GetMapping(path = "/numero/{numero}")
    @ApiOperation("Controlla se il numero inserito è un piatto esistente (ritorna un booleano)")
    public ResponseEntity<Boolean> getByNumero(@PathVariable int numero){
        boolean result = piattoService.checkByNumero(numero);
        if (result == true){
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    /*
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
     */

    @DeleteMapping(path = "/delete")
    @ApiOperation("Elimina il piatto")
    public ResponseEntity<Object> delete(@RequestBody String idStr){
        try{
            int id = Integer.parseInt(idStr);
            PiattoDTO dto = piattoService.delete(id);
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

    @PostMapping(path = "/update")
    @ApiOperation("Aggiorna un piatto")
    public ResponseEntity<Object> update(@RequestBody UpdatePiattoDTO dto){
        PiattoDTO result = piattoService.update(dto);
        if (dto == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @PostMapping(path = "/get")
    @ApiOperation("Ottieni i piatti di una certa tipologia SENZA i dati allergeni")
    public ResponseEntity<Object> get(@RequestBody GetPiattoDTO dto){
        List<PiattoDTO> result = piattoService.get(dto.getTipologia(), dto.getAllergeni());
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(result);
        }
    }

}
