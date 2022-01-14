package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.IndirizzoDAO;
import com.ragicriSushi.pw.DTO.IndirizzoDTO;
import com.ragicriSushi.pw.DTO.UtenteDTO;
import com.ragicriSushi.pw.Repository.IndirizzoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private Conversioni conversioni;

    public List<IndirizzoDTO> getAll(){
        return conversioni.toDTO(indirizzoRepository.findAll());
    }

    public IndirizzoDTO getById(int id){
        Optional<IndirizzoDAO> optional = indirizzoRepository.findById(id);

        if (optional.isPresent()) {
            return conversioni.toDTO(optional.get());
        }
        else {
            return null;
        }
    }

    public IndirizzoDTO update(IndirizzoDTO dto){
        Optional<IndirizzoDAO> dao = indirizzoRepository.findById(dto.getIdIndirizzo());
        if(dao.isPresent()){

            dao.get().setVia(dto.getVia());
            dao.get().setProvincia(dto.getProvincia());
            dao.get().setCAP(dto.getCAP());
            dao.get().setCivico(dto.getCivico());

            indirizzoRepository.save(dao.get());
            return conversioni.toDTO(dao.get());
        } else {
            return null;
        }
    }

}
