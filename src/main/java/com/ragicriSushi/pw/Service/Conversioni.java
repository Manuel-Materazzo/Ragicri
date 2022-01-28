package com.ragicriSushi.pw.Service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.ragicriSushi.pw.DAO.*;
import com.ragicriSushi.pw.DTO.Ordinazione.NewOrdinazioneDTO;
import com.ragicriSushi.pw.DTO.Ordinazione.OrdinazioneDTO;
import com.ragicriSushi.pw.DTO.OrdinazioniPiatto.PiattoOrdinatoDTO;
import com.ragicriSushi.pw.DTO.Piatto.PiattoDTO;
import com.ragicriSushi.pw.DTO.Role.RoleDTO;
import com.ragicriSushi.pw.DTO.Utente.IndirizzoDTO;
import com.ragicriSushi.pw.DTO.Utente.UtenteDTO;
import com.ragicriSushi.pw.Repository.*;
import com.ragicriSushi.pw.DTO.*;
import com.ragicriSushi.pw.Repository.IndirizzoRepository;
import com.ragicriSushi.pw.Repository.OrdinazioneRepository;
import com.ragicriSushi.pw.Repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Conversioni {

    @Autowired
    PiattoRepository piattoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OrdinazioneRepository ordinazioneRepository;
    @Autowired
    IndirizzoRepository indirizzoRepository;

    public <T, S> T toDTO(S dao) {
        if (dao instanceof PiattoDAO) {
            return (T) fromDaoToDto((PiattoDAO) dao);
        } else if (dao instanceof UtenteDAO) {
            return (T) fromDaoToDto((UtenteDAO) dao);
        } else if (dao instanceof IndirizzoDAO) {
            return (T) fromDaoToDto((IndirizzoDAO) dao);
        } else if (dao instanceof OrdinazioneDAO) {
            return (T) fromDaoToDto((OrdinazioneDAO) dao);
        } else if (dao instanceof RoleDAO) {
            return (T) fromDaoToDto((RoleDAO) dao);
        }
        return null;
    }

    public <T, S> T toDAO(S dto) {
        if (dto instanceof PiattoDTO) {
            return (T) fromDtoToDao((PiattoDTO) dto);
        } else if (dto instanceof UtenteDTO) {
            return (T) fromDtoToDao((UtenteDTO) dto);
        } else if (dto instanceof IndirizzoDTO) {
            return (T) fromDtoToDao((IndirizzoDTO) dto);
        } else if (dto instanceof NewOrdinazioneDTO) {
            return (T) fromDtoToDao((NewOrdinazioneDTO) dto);
        } else if (dto instanceof NewOrdinazioneIndirizzoDTO) {
            return (T) fromDtoToDao((NewOrdinazioneIndirizzoDTO) dto);
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
            } else if(dao instanceof IndirizzoDAO) {
                dtoList.add((T) fromDaoToDto((IndirizzoDAO) dao));
            } else if(dao instanceof OrdinazioneDAO) {
                dtoList.add((T) fromDaoToDto((OrdinazioneDAO) dao));
            } else if(dao instanceof RoleDAO) {
                dtoList.add((T) fromDaoToDto((RoleDAO) dao));
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
            } else if(dto instanceof NewOrdinazioneIndirizzoDTO) {
                daoList.add((T) fromDtoToDao((NewOrdinazioneIndirizzoDTO) dto));
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
        dto.setConsegnato(dao.getConsegnato());
        dto.setPreparato(dao.getPreparato());

        if (dao.getOrarioConsegna() == null){
            dto.setOrarioConsegna(null);
        }
        else {
            dto.setOrarioConsegna(dao.getOrarioConsegna().toString());
        }
        if (dao.getIndirizzo() == null){
            dto.setIdIndirizzo(0);
            dto.setIndirizzo(null);
        }
        else {
            dto.setIdIndirizzo(dao.getIndirizzo().getIdIndirizzo());
            dto.setIndirizzo(toDTO(dao.getIndirizzo()));
        }

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
        UtenteDTO dto = new UtenteDTO();

        dto.setId(dao.getIdUtente());
        dto.setNome(dao.getNome());

        RoleDTO roleDto=new RoleDTO();
        roleDto.setId(dao.getRuolo().getId());
        roleDto.setName(dao.getRuolo().getName());

        dto.setRuolo(roleDto);
        dto.setUsername(dao.getUsername());
        dto.setPassword((dao.getPassword()));
        dto.setIndirizzo(fromDaoToDto(dao.getIndirizzo()));

        return dto;
    }

    public IndirizzoDTO fromDaoToDto(IndirizzoDAO dao){
        IndirizzoDTO dto=new IndirizzoDTO();

        dto.setIdIndirizzo(dao.getIdIndirizzo());
        dto.setVia(dao.getVia());
        dto.setProvincia(dao.getProvincia());
        dto.setCAP(dao.getCAP());
        dto.setCivico(dao.getCivico());

        return dto;
    }

    private RoleDTO fromDaoToDto(RoleDAO dao) {
        RoleDTO dto= new RoleDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        return dto;
    }


    //TODO tutte le conversioni in DAO mancano
    public PiattoDAO fromDtoToDao(PiattoDTO dto){
        return null;
    }

    public UtenteDAO fromDtoToDao(UtenteDTO dto){
        UtenteDAO dao=new UtenteDAO();

        dao.setNome(dto.getNome());
        RoleDAO roleDao=new RoleDAO();
        roleDao.setId(dto.getRuolo().getId());
        roleDao.setName(dto.getRuolo().getName());
        IndirizzoDAO indirizzoDao= new IndirizzoDAO();
        dao.setIndirizzo(indirizzoRepository.getById(dto.getIndirizzo().getIdIndirizzo()));
        indirizzoDao.setCAP(dto.getIndirizzo().getCAP());
        indirizzoDao.setProvincia(dto.getIndirizzo().getProvincia());
        indirizzoDao.setCivico(dto.getIndirizzo().getCivico());
        indirizzoDao.setVia(dto.getIndirizzo().getVia());

        dao.setIndirizzo(indirizzoDao);
        dao.setRuolo(roleDao);
        dao.setUsername(dto.getUsername());
        dao.setPassword(dto.getPassword());
        return dao;
    }

    public IndirizzoDAO fromDtoToDao(IndirizzoDTO dto){
        IndirizzoDAO dao=new IndirizzoDAO();

        dao.setVia(dto.getVia());
        dao.setProvincia(dto.getProvincia());
        dao.setCAP(dto.getCAP());
        dao.setCivico(dto.getCivico());

        return dao;
    }

    public OrdinazioneDAO fromDtoToDao(NewOrdinazioneDTO dto) {
        OrdinazioneDAO dao = new OrdinazioneDAO();
        dao.setTavolo(dto.getTavolo());
        dao.setTipologia(dto.getTipologia());
        dao.setPagato(dto.getPagato());
        dao.setPersone(dto.getPersone());
        dao.setIdOrdinazione(0);
        dao.setOrarioConsegna(null);
        dao.setConsegnato(dto.getConsegnato());
        dao.setPreparato(dto.getPreparato());

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

    public OrdinazioneDAO fromDtoToDao(NewOrdinazioneIndirizzoDTO dto){
        Optional<IndirizzoDAO> indirizzoDao = indirizzoRepository.findById(dto.getIdIndirizzo());

        if(!indirizzoDao.isPresent()){
            return null;
        }

        OrdinazioneDAO dao = new OrdinazioneDAO();
        dao.setTavolo(dto.getTavolo());
        dao.setTipologia(dto.getTipologia());
        dao.setPagato(dto.getPagato());
        dao.setPersone(dto.getPersone());
        dao.setIndirizzo(indirizzoDao.get());
        dao.setOrarioConsegna(LocalTime.parse(dto.getOrarioConsegna()));
        dao.setConsegnato(dto.getConsegnato());
        dao.setPreparato(dto.getPreparato());

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
