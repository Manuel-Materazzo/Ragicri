package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.IndirizzoDAO;
import com.ragicriSushi.pw.DAO.PiattoDAO;
import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.DTO.AddUtenteDTO;
import com.ragicriSushi.pw.DTO.PiattoDTO;
import com.ragicriSushi.pw.DTO.UpdatePiattoDTO;
import com.ragicriSushi.pw.DTO.UtenteDTO;
import com.ragicriSushi.pw.Repository.IndirizzoRepository;
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
    private IndirizzoRepository indirizzoRepository;
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

    public UtenteDTO delete(int id){
        Optional<UtenteDAO> dao = utenteRepository.findById(id);

        if (dao.isPresent()){
            UtenteDTO dto = conversioni.toDTO(dao.get());
            utenteRepository.delete(dao.get());
            return dto;
        }
        else {
            return null;
        }
    }

    public UtenteDTO save(AddUtenteDTO dto) {
        IndirizzoDAO daoIndi = new IndirizzoDAO();
        daoIndi.setVia(dto.getIndirizzoDTO().getVia());
        daoIndi.setCivico(dto.getIndirizzoDTO().getCivico());
        daoIndi.setCAP(dto.getIndirizzoDTO().getCAP());
        daoIndi.setProvincia(dto.getIndirizzoDTO().getProvincia());
        daoIndi=indirizzoRepository.save(daoIndi);

        UtenteDAO dao = new UtenteDAO();
        dao.setNome(dto.getNome());
        dao.setRuolo(dto.getRuolo());
        dao.setEmail(dto.getEmail());
        dao.setUsername(dto.getUsername());
        dao.setPassword(dto.getPassword());
        dao.setIndirizzo(daoIndi);

        dao = utenteRepository.save(dao);
        return conversioni.toDTO(dao);
    }

    public UtenteDTO update(UtenteDTO dto){
        Optional<UtenteDAO> dao = utenteRepository.findById(dto.getId());
        if(dao.isPresent()){
            dao.get().setNome(dto.getNome());
            dao.get().setRuolo(dto.getRuolo());
            dao.get().setEmail(dto.getEmail());
            dao.get().setUsername(dto.getUsername());
            dao.get().setPassword(dto.getPassword());

            utenteRepository.save(dao.get());
            return conversioni.toDTO(dao.get());
        } else {
            return null;
        }
    }

    public boolean checkPresenzaUsername(String username){
        Optional<UtenteDAO> dao = utenteRepository.findUtenteByUsername(username);
        if(dao.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }
}

