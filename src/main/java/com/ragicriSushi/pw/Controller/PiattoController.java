package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DTO.NumeroDTO;
import com.ragicriSushi.pw.DTO.Piatto.AddPiattoDTO;
import com.ragicriSushi.pw.DTO.Piatto.GetPiattoDTO;
import com.ragicriSushi.pw.DTO.Piatto.PiattoDTO;
import com.ragicriSushi.pw.DTO.Piatto.TipologieDTO;
import com.ragicriSushi.pw.Service.PiattoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(piattoService.getAll());
    }

    @PreAuthorize("hasRole('ROLE_DIPENDENTE')")
    @GetMapping(path = "/{id}")
    @ApiOperation("Ritorna il piatto con l'id inserito")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        PiattoDTO dto = piattoService.getById(id);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Piatto non trovato.\"}");
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @PreAuthorize("hasRole('ROLE_DIPENDENTE')")
    @GetMapping(path = "/numero/{numero}")
    @ApiOperation("Controlla se il numero inserito è un piatto esistente (ritorna un booleano)")
    public ResponseEntity<Boolean> checkNumero(@PathVariable int numero) {
        boolean result = piattoService.checkByNumero(numero);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Aggiungi un piatto")
    public ResponseEntity<Object> add(@RequestPart("nomePiatto") String nomePiatto, @RequestPart("prezzoPiatto") String prezzoPiatto,
                                      @RequestPart("numeroPiatto") String numeroPiatto, @RequestPart("allergeniPiatto") String allergeniPiatto,
                                      @RequestPart("imgPiatto") MultipartFile file, @RequestPart("tipologiaPiatto") String tipologiaPiatto,
                                      @RequestPart("nomeFile") String nomeFile
    ) {
        AddPiattoDTO piattoAdd = new AddPiattoDTO();
        try {
            piattoAdd.setNome(nomePiatto);
            piattoAdd.setPrezzo(Double.parseDouble(prezzoPiatto));
            piattoAdd.setNumero(Integer.parseInt(numeroPiatto));
            piattoAdd.setAllergeni(allergeniPiatto);
            piattoAdd.setTipologia(tipologiaPiatto);
            String nomeImmagine = piattoService.saveImage(file, nomeFile);
            piattoAdd.setImg(nomeImmagine);
        } catch (Exception e) {
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/updateConFoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Aggiorna un piatto")
    public ResponseEntity<Object> update(@RequestPart("nomePiatto") String nomePiatto, @RequestPart("prezzoPiatto") String prezzoPiatto,
                                         @RequestPart("numeroPiatto") String numeroPiatto, @RequestPart("allergeniPiatto") String allergeniPiatto,
                                         @RequestPart("imgPiatto") MultipartFile file, @RequestPart("tipologiaPiatto") String tipologiaPiatto,
                                         @RequestPart("nomeFile") String nomeFile) {

        AddPiattoDTO piattoUpdate = new AddPiattoDTO();
        try {
            piattoUpdate.setNome(nomePiatto);
            piattoUpdate.setPrezzo(Double.parseDouble(prezzoPiatto));
            piattoUpdate.setNumero(Integer.parseInt(numeroPiatto));
            piattoUpdate.setAllergeni(allergeniPiatto);
            piattoUpdate.setTipologia(tipologiaPiatto);
            if (file != null) {
                String nomeImmagine = piattoService.saveImage(file, nomeFile);
                piattoUpdate.setImg(nomeImmagine);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        PiattoDTO result = piattoService.update(piattoUpdate);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\":\"Piatto non trovato.\"}");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_DIPENDENTE')")
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
