package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import com.ragicriSushi.pw.DTO.AddPiattiOrdinazioneDTO;
import com.ragicriSushi.pw.DTO.NewOrdinazioneDTO;
import com.ragicriSushi.pw.DTO.OrdinazioneDTO;
import com.ragicriSushi.pw.Service.OrdinazioneService;
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
        path = "/ordinazione",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class OrdinazioneController {

    @Autowired
    private OrdinazioneService ordinazioneService;

    @GetMapping(path = "")
    @ApiOperation("Ritorna tutte le ordinazioni")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(ordinazioneService.getAll());
    }

    @PostMapping(
            path = "addOrdinazione",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation("Aggiunge un'ordinazione")
    public ResponseEntity<Object> add(@RequestBody NewOrdinazioneDTO dto){
        return ResponseEntity.ok(ordinazioneService.add(dto));
    }

    @GetMapping(path = "pagato/{tavolo}")
    @ApiOperation("Imposta \"pagato\" a true per l'ultima ordinazione del tavolo inserito.")
    public ResponseEntity<Object> setPagato(@PathVariable int tavolo){
        if (ordinazioneService.checkTavolo(tavolo) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\":\"Tavolo non trovato.\"}");
        }

        OrdinazioneDTO dto = ordinazioneService.setPagato(tavolo);

        if (dto == null){
            return ResponseEntity.ok().body("{\"Status\":\"Ultima ordinazione per questo tavolo già pagata.\"}");
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "info/{tavolo}")
    @ApiOperation("Ritorna le principali informazioni per il cameriere sul tavolo passato.")
    public ResponseEntity<Object> infoTavolo(@PathVariable int tavolo){
        if (ordinazioneService.checkTavolo(tavolo) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\":\"Tavolo non trovato.\"}");
        }

        OrdinazioneDTO dto = ordinazioneService.info(tavolo);

        if (dto == null){
            return ResponseEntity.ok().body("{\"Status\":\"Tavolo libero.\"}");
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "nonPagato")
    @ApiOperation("Ritorna i tavoli che non hanno ancora pagato.")
    public ResponseEntity<Object> getNonPagato(){
        List<OrdinazioneDTO> dtoList = ordinazioneService.getNonPagato();

        if(dtoList == null){
            return ResponseEntity.ok().body("{\"Status\":\"Tutti i tavoli hanno già pagato.\"}");
        }
        else {
            return ResponseEntity.ok(dtoList);
        }
    }

}
