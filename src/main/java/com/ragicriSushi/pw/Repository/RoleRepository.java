package com.ragicriSushi.pw.Repository;

import com.ragicriSushi.pw.DAO.RoleDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleDAO, Integer> {

    Optional<RoleDAO> findByName(String nomeRuolo);
}
