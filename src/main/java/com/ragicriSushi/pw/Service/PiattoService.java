package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.PiattoDAO;
import com.ragicriSushi.pw.DTO.PiattoDTO;
import com.ragicriSushi.pw.Repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PiattoService {

    @Autowired
    private PiattoRepository piattoRepository;
    @Autowired
    private Conversioni conversioni;

    public List<PiattoDAO> getAll(){
        return conversioni.toDTO(piattoRepository.findAll());
    }

    public PiattoDTO getById(int id){
        Optional<PiattoDAO> optional = piattoRepository.findById(id);

        if (optional.isPresent()) {
            return conversioni.toDTO(optional.get());
        }
        else {
            return null;
        }
    }

    public List<PiattoDTO> getByTipologia(String tipologia){
        List<PiattoDAO> dao = piattoRepository.findPiattoByTipologia(tipologia);
        return conversioni.toDTO(dao);
    }

}
