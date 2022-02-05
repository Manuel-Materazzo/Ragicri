package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.IndirizzoDAO;
import com.ragicriSushi.pw.DAO.RoleDAO;
import com.ragicriSushi.pw.DAO.UtenteDAO;
import com.ragicriSushi.pw.DTO.Utente.*;
import com.ragicriSushi.pw.Repository.IndirizzoRepository;
import com.ragicriSushi.pw.Repository.RoleRepository;
import com.ragicriSushi.pw.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private PasswordEncoder passwordEncoder;

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
        if (dto.getIndirizzoDTO() != null) {
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
        RoleDAO roleDao = roleRepository.findByName(dto.getRuolo()).get();

        dao.setRuolo(roleDao);
        dao.setUsername(dto.getUsername());
        dao.setPassword(passwordEncoder.encode(dto.getPassword()));


        dao = utenteRepository.save(dao);
        return conversioni.toDTO(dao);
    }

    public UtenteDTO update(UpdateUtenteDto dto) {
        Optional<UtenteDAO> dao = utenteRepository.findById(dto.getId());
        if (dao.isPresent()) {
            if (dto.getNome() != null) {
                dao.get().setNome(dto.getNome());
            }
            if (dto.getIndirizzo() != null) {
                dao.get().getIndirizzo().setVia(dto.getIndirizzo().getVia());
                dao.get().getIndirizzo().setCivico(dto.getIndirizzo().getCivico());
                dao.get().getIndirizzo().setProvincia(dto.getIndirizzo().getProvincia());
                dao.get().getIndirizzo().setCAP(dto.getIndirizzo().getCAP());
            }
            if (dto.getEmail() != null) {
                dao.get().setEmail(dto.getEmail());
            }
            if (dto.getPassword() != null) {
                dao.get().setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            if (dto.getUsername() != null) {
                dao.get().setUsername(dto.getUsername());
            }
            if (dto.getRuolo() != null) {
                dao.get().setRuolo(roleRepository.findByName(dto.getRuolo()).get());
            }
            utenteRepository.save(dao.get());
            return conversioni.toDTO(dao.get());
        } else {
            return null;
        }
    }

    public Boolean checkPassword(String vecchiaPassword, String nuovaPassword) {
            String passwordCryptata=passwordEncoder.encode(nuovaPassword);
        if (passwordCryptata==vecchiaPassword) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPresenzaUsername(String username) {
        Optional<UtenteDAO> dao = utenteRepository.findUtenteByUsername(username);
        return dao.isPresent();
    }

    public boolean checkById(int id) {
        Optional<UtenteDAO> dao = utenteRepository.findById(id);
        return dao.isPresent();
    }

    public UtenteDTO getByUsername(String username) {
        Optional<UtenteDAO> dao = utenteRepository.findUtenteByUsername(username);
        if (!dao.isPresent()) {
            return null;
        } else {
            return conversioni.toDTO(dao.get());
        }
    }

    public IndirizzoDTO getIndirizzoByUsername(String username) {
        Optional<UtenteDAO> dao = utenteRepository.findUtenteByUsername(username);
        if (!dao.isPresent()) {
            return null;
        } else {
            return conversioni.toDTO(dao.get().getIndirizzo());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UtenteDAO> utente = utenteRepository.findUtenteByUsername(username);
        if (!utente.isPresent()) {
            throw new UsernameNotFoundException("Utente non trovato");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roleRepository.findAll().forEach(ruolo -> {
            authorities.add(new SimpleGrantedAuthority(
                    ruolo.getName()));
        });
        return new org.springframework.security.core.userdetails.User(utente.get().getUsername(),
                utente.get().getPassword(), authorities);
    }
}

