package com.ragicriSushi.pw.Repository;

import com.ragicriSushi.pw.DAO.UtenteDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<UtenteDAO, Integer> {

    List<UtenteDAO> findUtenteByRuolo(String ruolo);

    Optional<UtenteDAO> findUtenteByUsername(String username);

    Optional<UtenteDAO> findUtenteByEmail(String email);

    //ritorna tutti gli utenti dell'azienda(admin,dipendenti)
    @Query(
            value = "SELECT * FROM utente WHERE ruolo!=3 ",
            nativeQuery = true)
    List<UtenteDAO> getUtentiAzienda();
}
