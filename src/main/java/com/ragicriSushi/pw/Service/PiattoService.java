package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.PiattoDAO;
import com.ragicriSushi.pw.DTO.PiattoDTO;
import com.ragicriSushi.pw.DTO.UpdatePiattoDTO;
import com.ragicriSushi.pw.Repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PiattoService {

    @Autowired
    private PiattoRepository piattoRepository;
    @Autowired
    private Conversioni conversioni;

    public List<PiattoDTO> getAll(){
        return conversioni.toDTO(piattoRepository.findAll());
    }

    public PiattoDTO getById(int id){
        Optional<PiattoDAO> optional = piattoRepository.findById(id);

        if (optional.isPresent()) {
            return conversioni.toDTO(optional.get());
        }
        else {
            return null;
        }
    }

    public List<PiattoDTO> getByTipologia(String tipologia){
        List<PiattoDAO> dao = piattoRepository.findPiattoByTipologia(tipologia);
        return conversioni.toDTO(dao);
    }

    public List<PiattoDTO> get(String tipologia, String allergeni){
        List<PiattoDAO> dao = piattoRepository.findPiattoByTipologia(tipologia);

        if(dao == null){
            return null;
        }
        else {
            List<PiattoDAO> result = new ArrayList<>();
            for (int i = 0; i < dao.size(); i++) {
                if(!allergeni.equalsIgnoreCase(dao.get(i).getAllergeni())){
                    result.add(dao.get(i));
                }
            }
            return conversioni.toDTO(result);
        }
    }

    public PiattoDTO delete(int id){
        Optional<PiattoDAO> dao = piattoRepository.findById(id);

        if (dao.isPresent()){
            PiattoDTO dto = conversioni.toDTO(dao.get());
            piattoRepository.delete(dao.get());
            return dto;
        }
        else {
            return null;
        }
    }

    public PiattoDTO update(UpdatePiattoDTO dto){
        Optional<PiattoDAO> dao = piattoRepository.findById(dto.getId());
        if(dao.isPresent()){
            dao.get().setAllergeni(dto.getAllergeni());
            dao.get().setImg(dto.getImg());
            dao.get().setNome(dto.getNome());
            dao.get().setNumero(dto.getNumero());
            dao.get().setPrezzo(dto.getPrezzo());
            dao.get().setTipologia(dto.getTipologia());

            piattoRepository.save(dao.get());
            return conversioni.toDTO(dao.get());
        } else {
            return null;
        }
    }

}
