package com.ragicriSushi.pw.Repository;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdinazioneRepository extends JpaRepository<OrdinazioneDAO, Integer> {

    List<OrdinazioneDAO> findOrdinazioneByTavolo(int tavolo);

    List<OrdinazioneDAO> findOrdinazioneByPagato(boolean pagato);

    @Query(
            value = "SELECT * FROM ordinazione WHERE tipologia='Domicilio' OR tipologia='Asporto'",
            nativeQuery = true)
    List<OrdinazioneDAO> getAsportoDomicilio();

}
