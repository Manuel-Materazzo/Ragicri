package com.ragicriSushi.pw.Controller;

import com.ragicriSushi.pw.Repository.IndirizzoRepository;
import com.ragicriSushi.pw.Service.IndirizzoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
