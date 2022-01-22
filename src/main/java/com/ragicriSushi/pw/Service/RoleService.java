package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.RoleDAO;
import com.ragicriSushi.pw.DTO.Role.NameRuoloDTO;
import com.ragicriSushi.pw.DTO.Role.RoleAddDto;
import com.ragicriSushi.pw.DTO.Role.RoleDTO;
import com.ragicriSushi.pw.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Conversioni conversioni;

    public List<RoleDTO> getAll() {
        return conversioni.toDTO(roleRepository.findAll());
    }

    public RoleDTO getById(int id) {
        Optional<RoleDAO> optional = roleRepository.findById(id);

        if (optional.isPresent()) {
            return conversioni.toDTO(optional.get());
        } else {
            return null;
        }
    }

    public RoleDTO getByName(String name) {
        Optional<RoleDAO> optional = roleRepository.findByName(name);

        if (optional.isPresent()) {
            return conversioni.toDTO(optional.get());
        } else {
            return null;
        }
    }

    public boolean checkByName(String name) {
        Optional<RoleDAO> dao = roleRepository.findByName(name);

        return dao.isPresent();
    }

    public RoleDTO addRuolo(RoleAddDto dto) {
        if (checkByName(dto.getName())) {
            return null;
        }
        RoleDAO dao = new RoleDAO();
        dao.setName(dto.getName());
        roleRepository.save(dao);
        return conversioni.toDTO(dao);
    }

    public RoleDTO delete(NameRuoloDTO name) {
        Optional<RoleDAO> dao = roleRepository.findByName(name.getName());

        if (dao.isPresent()) {
            RoleDTO dto = conversioni.toDTO(dao.get());
            roleRepository.delete(dao.get());
            return dto;
        } else {
            return null;
        }
    }

}
