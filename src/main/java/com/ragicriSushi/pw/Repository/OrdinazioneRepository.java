package com.ragicriSushi.pw.Repository;

import com.ragicriSushi.pw.DAO.OrdinazioneDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdinazioneRepository extends JpaRepository<OrdinazioneDAO, Integer> {

    List<OrdinazioneDAO> findOrdinazioneByTavolo(int tavolo);

}
