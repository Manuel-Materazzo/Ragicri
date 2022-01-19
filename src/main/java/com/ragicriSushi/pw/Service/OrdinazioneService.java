package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import com.ragicriSushi.pw.DAO.PiattoDAO;
import com.ragicriSushi.pw.DAO.PiattoOrdinato;
import com.ragicriSushi.pw.DTO.NewOrdinazioneDTO;
import com.ragicriSushi.pw.DTO.OrdinazioneDTO;
import com.ragicriSushi.pw.Repository.OrdinazioneRepository;
import com.ragicriSushi.pw.Repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdinazioneService {

    @Autowired
    private OrdinazioneRepository ordinazioneRepository;
    @Autowired
    private Conversioni conversioni;
    @Autowired
    private PiattoRepository piattoRepository;

    public List<OrdinazioneDTO> getAll() {
        return conversioni.toDTO(ordinazioneRepository.findAll());
    }

    public OrdinazioneDTO add(NewOrdinazioneDTO dto) {
        List<OrdinazioneDAO> daoList = ordinazioneRepository.findOrdinazioneByTavolo(dto.getTavolo());

        if (daoList.isEmpty() || daoList.get(daoList.size() - 1).getPagato() == true) {
            OrdinazioneDAO dao = conversioni.toDAO(dto);
            ordinazioneRepository.save(dao);

            return conversioni.toDTO(dao);
        } else {
            OrdinazioneDAO dao = daoList.get(daoList.size() - 1);
            List<PiattoOrdinato> piattiOrdinati = dao.getPiattiOrdinati();

            for (int i = 0; i < dto.getPiattiOrdinati().size(); i++) {
                Optional<PiattoDAO> optionalDao = piattoRepository.findPiattoByNumero(dto.getPiattiOrdinati().get(i).getNumeretto());
                if (!optionalDao.isPresent()) {
                    continue;
                }
                PiattoOrdinato piattoOrdinato = new PiattoOrdinato();
                piattoOrdinato.setOrdinazione(dao);
                piattoOrdinato.setPiatto(piattoRepository.findPiattoByNumero(dto.getPiattiOrdinati().get(i).getNumeretto()).get());
                piattoOrdinato.setQuantita(dto.getPiattiOrdinati().get(i).getQuantita());
                piattoOrdinato.setConsegnato(false);

                piattiOrdinati.add(piattoOrdinato);
            }
            dao.setPiattiOrdinati(piattiOrdinati);

            ordinazioneRepository.save(dao);
            return conversioni.toDTO(dao);
        }
    }

    public OrdinazioneDTO setPagato(int tavolo) {
        List<OrdinazioneDAO> daoList = ordinazioneRepository.findOrdinazioneByTavolo(tavolo);
        OrdinazioneDAO dao = daoList.get(daoList.size() - 1);

        if (dao.getPagato() == true) {
            return null;
        } else {
            dao.setPagato(true);
            ordinazioneRepository.save(dao);
            return conversioni.toDTO(dao);
        }
    }

    public OrdinazioneDTO info(int tavolo) {
        List<OrdinazioneDAO> daoList = ordinazioneRepository.findOrdinazioneByTavolo(tavolo);
        OrdinazioneDAO dao = daoList.get(daoList.size() - 1);

        if (dao.getPagato() == true) {
            return null;
        } else {
            return conversioni.toDTO(dao);
        }
    }

    public OrdinazioneDTO infoOrd(int id) {
        Optional<OrdinazioneDAO> dao = ordinazioneRepository.findById(id);

        if (dao.isPresent()) {
            return conversioni.toDTO(dao.get());
        } else {
            return null;
        }
    }

    public boolean checkTavolo(int tavolo) {
        List<OrdinazioneDAO> daoList = ordinazioneRepository.findOrdinazioneByTavolo(tavolo);

        if (daoList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<OrdinazioneDTO> getNonPagato() {
        List<OrdinazioneDAO> daoList = ordinazioneRepository.findOrdinazioneByPagato(false);

        if (daoList.isEmpty()) {
            return null;
        } else {
            return conversioni.toDTO(daoList);
        }
    }

    public OrdinazioneDTO setConsegnato(int tavolo) {
        List<OrdinazioneDAO> daoList = ordinazioneRepository.findOrdinazioneByTavolo(tavolo);
        OrdinazioneDAO dao = daoList.get(daoList.size() - 1);

        if (dao.getPagato() == true) {
            return null;
        } else {
            for (int i = 0; i < dao.getPiattiOrdinati().size(); i++) {
                dao.getPiattiOrdinati().get(i).setConsegnato(true);
            }
            ordinazioneRepository.save(dao);
            return conversioni.toDTO(dao);
        }
    }

    public OrdinazioneDTO setConsegnatoId(int id) {
        Optional<OrdinazioneDAO> dao = ordinazioneRepository.findById(id);

        if (dao.isPresent()) {
            for (int i = 0; i < dao.get().getPiattiOrdinati().size(); i++) {
                dao.get().getPiattiOrdinati().get(i).setConsegnato(true);
            }
            ordinazioneRepository.save(dao.get());
            return conversioni.toDTO(dao.get());
        } else {
            return null;
        }
    }

    public List<OrdinazioneDTO> getAsportoDomicilio() {
        List<OrdinazioneDAO> daoList = ordinazioneRepository.getAsportoDomicilio();

        if (daoList == null) {
            return null;
        } else {
            return conversioni.toDTO(daoList);
        }
    }

}
