package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import com.ragicriSushi.pw.DTO.NewOrdinazioneDTO;
import com.ragicriSushi.pw.DTO.OrdinazioneDTO;
import com.ragicriSushi.pw.Repository.OrdinazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdinazioneService {

    @Autowired
    private OrdinazioneRepository ordinazioneRepository;
    @Autowired
    private Conversioni conversioni;

    public List<OrdinazioneDTO> getAll(){
        return conversioni.toDTO(ordinazioneRepository.findAll());
    }

    public OrdinazioneDTO add(NewOrdinazioneDTO dto){
        OrdinazioneDAO dao = conversioni.toDAO(dto);
        ordinazioneRepository.save(dao);

        return conversioni.toDTO(dao);
    }

}
