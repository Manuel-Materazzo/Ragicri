package com.ragicriSushi.pw.Repository;

import com.ragicriSushi.pw.DAO.PiattoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PiattoRepository extends JpaRepository<PiattoDAO, Integer> {

    List<PiattoDAO> findPiattoByTipologia(String tipologia);

    Optional<PiattoDAO> findPiattoByNumero(int numero);

    @Query(
            value = "SELECT * FROM piatto GROUP BY tipologia",
            nativeQuery = true)
    List<PiattoDAO> getTipologie();

}
