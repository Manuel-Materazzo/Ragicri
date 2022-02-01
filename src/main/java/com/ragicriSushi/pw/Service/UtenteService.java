package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.IndirizzoDAO;
import com.ragicriSushi.pw.DAO.RoleDAO;
import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.DTO.Utente.AddUtenteDTO;
import com.ragicriSushi.pw.DTO.Utente.IndirizzoDTO;
import com.ragicriSushi.pw.DTO.Utente.UtenteDTO;
import com.ragicriSushi.pw.Repository.IndirizzoRepository;
import com.ragicriSushi.pw.Repository.RoleRepository;
import com.ragicriSushi.pw.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UtenteService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private Conversioni conversioni;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public List<UtenteDTO> getAll() {
        return conversioni.toDTO(utenteRepository.findAll());
    }

    public List<UtenteDTO> getAllUtentiAzienda() {
        return conversioni.toDTO(utenteRepository.getUtentiAzienda());
    }
    public UtenteDTO getById(int id) {
        Optional<UtenteDAO> optional = utenteRepository.findById(id);

        if (optional.isPresent()) {
            return conversioni.toDTO(optional.get());
        } else {
            return null;
        }
    }

    public List<UtenteDTO> getByRuolo(String ruolo) {
        List<UtenteDAO> dao = utenteRepository.findUtenteByRuolo(ruolo);
        return conversioni.toDTO(dao);
    }

    public UtenteDTO delete(int id) {
        Optional<UtenteDAO> dao = utenteRepository.findById(id);

        if (dao.isPresent()) {
            UtenteDTO dto = conversioni.toDTO(dao.get());
            utenteRepository.delete(dao.get());
            return dto;
        } else {
            return null;
        }
    }

    public UtenteDTO save(AddUtenteDTO dto) {

        UtenteDAO dao = new UtenteDAO();
        if(dto.getIndirizzoDTO()!=null) {
            IndirizzoDAO daoIndi = new IndirizzoDAO();

            daoIndi.setVia(dto.getIndirizzoDTO().getVia());
            daoIndi.setCivico(dto.getIndirizzoDTO().getCivico());
            daoIndi.setCAP(dto.getIndirizzoDTO().getCAP());
            daoIndi.setProvincia(dto.getIndirizzoDTO().getProvincia());

            daoIndi = indirizzoRepository.save(daoIndi);
            dao.setIndirizzo(daoIndi);
        }
        dao.setNome(dto.getNome());
        dao.setEmail(dto.getEmail());
        RoleDAO roleDao = new RoleDAO();
        roleDao.setId(dto.getRuolo().getId());
        roleDao.setName(dto.getRuolo().getName());

        dao.setRuolo(roleDao);
        dao.setUsername(dto.getUsername());
        dao.setPassword(passwordEncoder.encode(dto.getPassword()));


        dao = utenteRepository.save(dao);
        return conversioni.toDTO(dao);
    }

    public UtenteDTO update(UtenteDTO dto) {
        Optional<UtenteDAO> dao = utenteRepository.findById(dto.getId());
        if (dao.isPresent()) {
            UtenteDAO utente = conversioni.toDAO(dto);
            utenteRepository.save(utente);
            return conversioni.toDTO(utente);
        } else {
            return null;
        }
    }

    public boolean checkPresenzaUsername (String username) {
        Optional<UtenteDAO> dao = utenteRepository.findUtenteByUsername(username);
        return dao.isPresent();
    }

    public UtenteDTO getByUsername (String username){
        Optional<UtenteDAO> dao = utenteRepository.findUtenteByUsername(username);
        if (!dao.isPresent()){
            return null;
        }
        else {
            return conversioni.toDTO(dao.get());
        }
    }

    public IndirizzoDTO getIndirizzoByUsername (String username){
        Optional<UtenteDAO> dao = utenteRepository.findUtenteByUsername(username);
        if (!dao.isPresent()){
            return null;
        }
        else {
            return conversioni.toDTO(dao.get().getIndirizzo());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UtenteDAO> utente=utenteRepository.findUtenteByUsername(username);
        if(!utente.isPresent())
        {
            throw new UsernameNotFoundException("Utente non trovato");
        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        roleRepository.findAll().forEach(ruolo ->{ authorities.add(new SimpleGrantedAuthority(
                ruolo.getName()));
        });
        return new org.springframework.security.core.userdetails.User(utente.get().getUsername(),
                utente.get().getPassword(),authorities);
    }
}

