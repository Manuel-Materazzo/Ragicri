package com.ragicriSushi.pw.Repository;

import com.ragicriSushi.pw.DAO.UtenteDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtenteRepository extends JpaRepository<UtenteDAO, Integer> {

    List<UtenteDAO> findUtenteByRuolo(String ruolo);
}
