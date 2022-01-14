package com.ragicriSushi.pw.Repository;

import com.ragicriSushi.pw.DAO.UtenteDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<UtenteDAO, Integer> {

    List<UtenteDAO> findUtenteByRuolo(String ruolo);

    Optional<UtenteDAO> findUtenteByUsername(String username);
}
