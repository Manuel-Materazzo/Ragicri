package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.PiattoDAO;
import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.DTO.PiattoDTO;
import com.ragicriSushi.pw.DTO.UtenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Conversioni {

    @Autowired
    PiattoService piattoService;

    public <T, S> T toDTO(S dao) {
        if (dao instanceof PiattoDAO) {
            return (T) fromDaoToDto((PiattoDAO) dao);
        } else if (dao instanceof UtenteDAO) {
            return (T) fromDaoToDto((UtenteDAO) dao);
        }
        return null;
    }

    public <T, S> List<T> toDTO(List<S> daoList){
        List<T> dtoList = new ArrayList<T>();
        for(S dao: daoList) {
            if(dao instanceof PiattoDAO) {
                dtoList.add((T) fromDaoToDto((PiattoDAO) dao));
            } else if(dao instanceof UtenteDAO) {
                dtoList.add((T) fromDaoToDto((UtenteDAO) dao));
            }
        }
        return dtoList;
    }

    //TODO toDAO anche per le liste

    public PiattoDTO fromDaoToDto(PiattoDAO dao){
        PiattoDTO dto = new PiattoDTO();
        dto.setNome(dao.getNome());
        dto.setNumero(dao.getNumero());
        dto.setTipologia(dao.getTipologia());
        dto.setAllergeni(dao.getAllergeni());
        dto.setConsegnato(dao.getConsegnato());
        dto.setImg(dao.getImg());
        return dto;
    }

    public UtenteDTO fromDaoToDto(UtenteDAO dao){
        return null;    //TODO fromDaoToDto di utente
    }

    //TODO tutte le conversioni in DAO mancano
    public PiattoDAO fromDtoToDao(PiattoDTO dto){
        return null;
    }

    public UtenteDAO fromDtoToDao(UtenteDTO dto){
        return null;
    }

}
