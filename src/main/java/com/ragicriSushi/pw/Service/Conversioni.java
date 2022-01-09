package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import com.ragicriSushi.pw.DAO.PiattoDAO;
import com.ragicriSushi.pw.DAO.PiattoOrdinato;
import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.DTO.*;
import com.ragicriSushi.pw.Repository.OrdinazioneRepository;
import com.ragicriSushi.pw.Repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Conversioni {

    @Autowired
    PiattoRepository piattoRepository;
    @Autowired
    OrdinazioneRepository ordinazioneRepository;

    public <T, S> T toDTO(S dao) {
        if (dao instanceof PiattoDAO) {
            return (T) fromDaoToDto((PiattoDAO) dao);
        } else if (dao instanceof UtenteDAO) {
            return (T) fromDaoToDto((UtenteDAO) dao);
        } else if (dao instanceof OrdinazioneDAO) {
            return (T) fromDaoToDto((OrdinazioneDAO) dao);
        }
        return null;
    }

    public <T, S> T toDAO(S dto) {
        if (dto instanceof PiattoDTO) {
            return (T) fromDtoToDao((PiattoDTO) dto);
        } else if (dto instanceof UtenteDTO) {
            return (T) fromDtoToDao((UtenteDTO) dto);
        } else if (dto instanceof NewOrdinazioneDTO) {
            return (T) fromDtoToDao((NewOrdinazioneDTO) dto);
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
            } else if(dao instanceof OrdinazioneDAO) {
                dtoList.add((T) fromDaoToDto((OrdinazioneDAO) dao));
            }
        }
        return dtoList;
    }

    public <T, S> List<T> toDAO(List<S> dtoList){
        List<T> daoList = new ArrayList<T>();
        for(S dto: dtoList) {
            if(dto instanceof PiattoDTO) {
                daoList.add((T) fromDtoToDao((PiattoDTO) dto));
            } else if(dto instanceof UtenteDTO) {
                daoList.add((T) fromDtoToDao((UtenteDTO) dto));
            } else if(dto instanceof NewOrdinazioneDTO) {
                daoList.add((T) fromDtoToDao((NewOrdinazioneDTO) dto));
            }
        }
        return daoList;
    }

    //TODO toDAO anche per le liste

    public PiattoDTO fromDaoToDto(PiattoDAO dao){
        PiattoDTO dto = new PiattoDTO();
        dto.setNome(dao.getNome());
        dto.setNumero(dao.getNumero());
        dto.setTipologia(dao.getTipologia());
        dto.setAllergeni(dao.getAllergeni());
        dto.setImg(dao.getImg());
        dto.setPrezzo(dao.getPrezzo());
        return dto;
    }

    public OrdinazioneDTO fromDaoToDto(OrdinazioneDAO dao){
        OrdinazioneDTO dto = new OrdinazioneDTO();
        dto.setIdOrdinazione(dao.getIdOrdinazione());
        dto.setTipologia(dao.getTipologia());
        dto.setTavolo(dao.getTavolo());
        dto.setPersone(dao.getPersone());
        dto.setPagato(dao.getPagato());

        List<PiattoOrdinatoDTO> listaPiattiOrdinati = new ArrayList<>();
        for (int i = 0; i < dao.getPiattiOrdinati().size(); i++) {
            PiattoOrdinatoDTO piattoOrdinato = new PiattoOrdinatoDTO();
            piattoOrdinato.setId(dao.getPiattiOrdinati().get(i).getId());
            piattoOrdinato.setNumeretto(dao.getPiattiOrdinati().get(i).getPiatto().getNumero());
            piattoOrdinato.setPrezzo(dao.getPiattiOrdinati().get(i).getPiatto().getPrezzo());
            piattoOrdinato.setNome(dao.getPiattiOrdinati().get(i).getPiatto().getNome());
            piattoOrdinato.setQuantita(dao.getPiattiOrdinati().get(i).getQuantita());
            piattoOrdinato.setConsegnato(dao.getPiattiOrdinati().get(i).getConsegnato());
            listaPiattiOrdinati.add(piattoOrdinato);
        }
        dto.setPiattiOrdinati(listaPiattiOrdinati);

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

    public OrdinazioneDAO fromDtoToDao(NewOrdinazioneDTO dto) {
        OrdinazioneDAO dao = new OrdinazioneDAO();
        dao.setTavolo(dto.getTavolo());
        dao.setTipologia(dto.getTipologia());
        dao.setPagato(dto.getPagato());
        dao.setPersone(dto.getPersone());

        List<PiattoOrdinato> piattiOrdinati = new ArrayList<>();
        for (int i = 0; i < dto.getPiattiOrdinati().size(); i++) {
            PiattoOrdinato piattoOrdinato = new PiattoOrdinato();
            piattoOrdinato.setOrdinazione(dao);
            piattoOrdinato.setPiatto(piattoRepository.findPiattoByNumero(dto.getPiattiOrdinati().get(i).getNumeretto()).get());
            piattoOrdinato.setQuantita(dto.getPiattiOrdinati().get(i).getQuantita());
            piattoOrdinato.setConsegnato(false);
            piattiOrdinati.add(piattoOrdinato);
        }
        dao.setPiattiOrdinati(piattiOrdinati);

        return dao;
    }

}
