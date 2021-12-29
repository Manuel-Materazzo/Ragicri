package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import com.ragicriSushi.pw.DTO.NewOrdinazioneDTO;
import com.ragicriSushi.pw.Service.OrdinazioneService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

}
