package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<UtenteDAO> getAll(){
        return utenteRepository.findAll();
    }

}
