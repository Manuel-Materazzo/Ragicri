package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DTO.IndirizzoDTO;
import com.ragicriSushi.pw.Repository.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private Conversioni conversioni;

    public List<IndirizzoDTO> getAll(){
        return conversioni.toDTO(indirizzoRepository.findAll());
    }

}
