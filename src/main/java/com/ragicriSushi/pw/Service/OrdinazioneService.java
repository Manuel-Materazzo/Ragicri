package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import com.ragicriSushi.pw.DAO.PiattoOrdinato;
import com.ragicriSushi.pw.DTO.AddPiattiOrdinazioneDTO;
import com.ragicriSushi.pw.DTO.NewOrdinazioneDTO;
import com.ragicriSushi.pw.DTO.OrdinazioneDTO;
import com.ragicriSushi.pw.Repository.OrdinazioneRepository;
import com.ragicriSushi.pw.Repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdinazioneService {

    @Autowired
    private OrdinazioneRepository ordinazioneRepository;
    @Autowired
    private Conversioni conversioni;
    @Autowired
    private PiattoRepository piattoRepository;

    public List<OrdinazioneDTO> getAll(){
        return conversioni.toDTO(ordinazioneRepository.findAll());
    }

    public OrdinazioneDTO add(NewOrdinazioneDTO dto){
        List<OrdinazioneDAO> daoList = ordinazioneRepository.findOrdinazioneByTavolo(dto.getTavolo());

        if(daoList.get(daoList.size()-1).getPagato() == true){
            OrdinazioneDAO dao = conversioni.toDAO(dto);
            ordinazioneRepository.save(dao);

            return conversioni.toDTO(dao);
        }
        else {
            OrdinazioneDAO dao = daoList.get(daoList.size()-1);
            List<PiattoOrdinato> piattiOrdinati = dao.getPiattiOrdinati();

            for (int i = 0; i < dto.getPiattiOrdinati().size(); i++) {
                PiattoOrdinato piattoOrdinato = new PiattoOrdinato();
                piattoOrdinato.setOrdinazione(dao);
                piattoOrdinato.setPiatto(piattoRepository.findPiattoByNumero(dto.getPiattiOrdinati().get(i).getNumeretto()));
                piattoOrdinato.setQuantita(dto.getPiattiOrdinati().get(i).getQuantita());
                piattoOrdinato.setConsegnato(false);

                piattiOrdinati.add(piattoOrdinato);
            }
            dao.setPiattiOrdinati(piattiOrdinati);

            ordinazioneRepository.save(dao);
            return conversioni.toDTO(dao);
        }
    }

}
