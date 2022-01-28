package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.*;
import com.ragicriSushi.pw.Service.PiattoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
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
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(piattoService.getAll());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Ritorna il piatto con l'id inserito")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        PiattoDTO dto = piattoService.getById(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Piatto non trovato.");
            // funziona ma ritorna un risultato raw, da quindi convertire in JSON, gl&hf
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(path = "/numero/{numero}")
    @ApiOperation("Controlla se il numero inserito è un piatto esistente (ritorna un booleano)")
    public ResponseEntity<Boolean> checkNumero(@PathVariable int numero) {
        boolean result = piattoService.checkByNumero(numero);
        if (result == true) {
            return ResponseEntity.ok(true);
        } else {
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

    @PostMapping(path = "/delete")
    @ApiOperation("Elimina il piatto")
    public ResponseEntity<Object> delete(@RequestBody NumeroDTO dto) {
        PiattoDTO result = piattoService.delete(dto);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Piatto non trovato.\"}");
        }
    }

    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Aggiungi un piatto")
    public ResponseEntity<Object> add(@RequestPart("nomePiatto") String nomePiatto, @RequestPart("prezzoPiatto") String prezzoPiatto,
                                      @RequestPart("numeroPiatto") String numeroPiatto, @RequestPart("allergeniPiatto") String allergeniPiatto,
                                      @RequestPart("imgPiatto") MultipartFile file, @RequestPart("tipologiaPiatto") String tipologiaPiatto,
                                      @RequestPart("nomeFile") String nomeFile
    ) {
        AddPiattoDTO piattoAdd= new AddPiattoDTO();
        try {
            piattoAdd.setNome(nomePiatto);
            piattoAdd.setPrezzo(Double.parseDouble(prezzoPiatto));
            piattoAdd.setNumero(Integer.parseInt(numeroPiatto));
            piattoAdd.setAllergeni(allergeniPiatto);
            piattoAdd.setTipologia(tipologiaPiatto);
            String nomeImmagine= piattoService.saveImage(file,nomeFile);
            piattoAdd.setImg(nomeImmagine);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        PiattoDTO result = piattoService.add(piattoAdd);
        if (result == null) {
            return ResponseEntity.badRequest().body("{\"status\": \"Numero già presente.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(piattoAdd);
        }
    }

    @PostMapping(path = "/update")
    @ApiOperation("Aggiorna un piatto")
    public ResponseEntity<Object> update(@RequestBody AddPiattoDTO dto) {
        PiattoDTO result = piattoService.update(dto);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Piatto non trovato.\"}");
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @PostMapping(path = "/get")
    @ApiOperation("Ottieni i piatti di una certa tipologia SENZA i dati allergeni")
    public ResponseEntity<Object> get(@RequestBody GetPiattoDTO dto) {
        List<PiattoDTO> result = piattoService.get(dto.getTipologia(), dto.getAllergeni());
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Piatto non trovato.\"}");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping(path = "/getByNumero/{numero}")
    @ApiOperation("Restituisce il piatto con il numero inserito")
    public ResponseEntity<Object> getByNumero(@PathVariable int numero) {
        PiattoDTO result = piattoService.getNumero(numero);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Piatto non trovato.\"}");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping(path = "/tipologie")
    @ApiOperation("Ritorna un JSON contenente una lista con tutte le tipolgoie)")
    public ResponseEntity<Object> getTipologie() {
        TipologieDTO dto = piattoService.getTipologie();

        return ResponseEntity.ok(dto);
    }

}
