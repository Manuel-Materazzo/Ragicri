package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import com.ragicriSushi.pw.DTO.*;
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

    @PostMapping(path = "addOrdinazioneIndirizzo")
    @ApiOperation("Aggiunge un'ordinazione da Asporto/Domicilio (richiede l'id dell'indirizzo)")
    public ResponseEntity<Object> addConIndirizzo(@RequestBody NewOrdinazioneIndirizzoDTO dto){
        OrdinazioneDTO result = ordinazioneService.addConIndirizzo(dto);
        if (result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Indirizzo non trovato.\"}");
        }
        else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping(path = "pagato/{tavolo}")
    @ApiOperation("Imposta \"pagato\" a true per l'ultima ordinazione del tavolo inserito.")
    public ResponseEntity<Object> setPagato(@PathVariable int tavolo){
        if (ordinazioneService.checkTavolo(tavolo) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Tavolo non trovato.\"}");
        }

        OrdinazioneDTO dto = ordinazioneService.setPagato(tavolo);

        if (dto == null){
            return ResponseEntity.ok().body("{\"status\":\"Ultima ordinazione per questo tavolo già pagata.\"}");
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "info/{tavolo}")
    @ApiOperation("Ritorna le principali informazioni per il cameriere sul tavolo passato.")
    public ResponseEntity<Object> infoTavolo(@PathVariable int tavolo){
        if (ordinazioneService.checkTavolo(tavolo) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Tavolo non trovato.\"}");
        }

        OrdinazioneDTO dto = ordinazioneService.info(tavolo);

        if (dto == null){
            return ResponseEntity.ok().body("{\"status\":\"Tavolo libero.\"}");
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "infoOrd/{id}")
    @ApiOperation("Ritorna tutte le informazio su di un'ordinazione.")
    public ResponseEntity<Object> infoOrd(@PathVariable int id){
        OrdinazioneDTO dto = ordinazioneService.infoOrd(id);

        if (dto == null){
            return ResponseEntity.ok().body("{\"status\":\"Ordinazione non trovata.\"}");
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
            return ResponseEntity.ok().body("{\"status\":\"Tutti i tavoli hanno già pagato.\"}");
        }
        else {
            return ResponseEntity.ok(dtoList);
        }
    }

    @PostMapping(path = "consegnato")
    @ApiOperation("Imposta l'attributo \"Consegnato\" a true degli elementi passati.")
    public ResponseEntity<Object> setConsegnati(@RequestBody TavoloDTO dto){
        if (ordinazioneService.checkTavolo(dto.getTavolo()) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Tavolo non trovato.\"}");
        }

        OrdinazioneDTO result = ordinazioneService.setConsegnatoPiatti(dto.getTavolo());

        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "consegnatoId/{id}")
    @ApiOperation("Importa l'attributo \"Consegato\" ai piatti dell'ordinazione passata.")
    public ResponseEntity<Object> setConsegnatiId(@PathVariable int id){
        OrdinazioneDTO dto = ordinazioneService.setConsegnatoId(id);

        if(dto == null){
            return ResponseEntity.ok().body("{\"status\":\"Ordinazione non trovata.\"}");
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "asportoDomicilio")
    @ApiOperation("Ritorna tutte le ordinazioni da Asporto/Domicilio.")
    public ResponseEntity<Object> getAsportoDomicilio(){
        List<OrdinazioneDTO> dtoList = ordinazioneService.getAsportoDomicilio();

        if(dtoList == null){
            return ResponseEntity.ok().body("{\"status\":\"Non ci sono ordinazioni.\"}");
        }
        else {
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping(path = "asportoDomicilioNonConsegnato")
    @ApiOperation("Ritorna Le ordinazioni da Asporto/Domicilio ancora non consegnate.")
    public ResponseEntity<Object> getAsportoDomicilioNonConsegnato(){
        List<OrdinazioneDTO> dtoList = ordinazioneService.getAsportoDomicilioNonConsegnato();

        if(dtoList == null){
            return ResponseEntity.ok().body("{\"status\":\"Non ci sono ordinazioni.\"}");
        }
        else {
            return ResponseEntity.ok(dtoList);
        }
    }

    @PostMapping(path = "setPreparato")
    @ApiOperation("Imposta il campo preparato a true.")
    public ResponseEntity<Object> setPreparato(@RequestBody IdOrdinazioneDTO dto){
        OrdinazioneDTO result = ordinazioneService.setPreparato(dto.getIdOrdinazione());

        if(result == null){
            return ResponseEntity.ok().body("{\"status\":\"Ordinazione non trovata.\"}");
        }
        else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping(path = "setConsegnato")
    @ApiOperation("Imposta il campo consegnato a true.")
    public ResponseEntity<Object> setConsegnato(@RequestBody IdOrdinazioneDTO dto){
        OrdinazioneDTO result = ordinazioneService.setConsegnato(dto.getIdOrdinazione());

        if(result == null){
            return ResponseEntity.ok().body("{\"status\":\"Ordinazione non trovata.\"}");
        }
        else {
            return ResponseEntity.ok(result);
        }
    }

}
