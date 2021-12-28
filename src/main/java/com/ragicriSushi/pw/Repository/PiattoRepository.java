package com.ragicriSushi.pw.Repository;

import com.ragicriSushi.pw.DAO.PiattoDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PiattoRepository extends JpaRepository<PiattoDAO, Integer> {

    List<PiattoDAO> findPiattoByTipologia(String tipologia);

}
