package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.PiattoDAO;
import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.DTO.PiattoDTO;
import com.ragicriSushi.pw.DTO.UtenteDTO;
import com.ragicriSushi.pw.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private Conversioni conversioni;

    public List<UtenteDTO> getAll(){
        return conversioni.toDTO(utenteRepository.findAll());
    }

    public UtenteDTO getById(int id){
        Optional<UtenteDAO> optional = utenteRepository.findById(id);

        if (optional.isPresent()) {
            return conversioni.toDTO(optional.get());
        }
        else {
            return null;
        }
    }

    public List<UtenteDTO> getByRuolo(String ruolo){
        List<UtenteDAO> dao = utenteRepository.findUtenteByRuolo(ruolo);
        return conversioni.toDTO(dao);
    }
}
