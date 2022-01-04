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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<PiattoDAO> dao;

        if (tipologia == ""){
            dao = piattoRepository.findAll();
        }
        else {
            dao = piattoRepository.findPiattoByTipologia(tipologia);
            if(dao == null){
                return null;
            }
        }

        if(allergeni == ""){
            return conversioni.toDTO(dao);
        }

        String[] parts = allergeni.toLowerCase().split("\\s+");
        int cont = 0;
        List<PiattoDAO> result = new ArrayList<>();
        for (int i = 0; i < dao.size(); i++) {
            System.out.println("allergeni elemento in posizione " + i + ": " + dao.get(i).getAllergeni());
            /*
            if(dao.get(i).getAllergeni() == "" || dao.get(i).getAllergeni() == null){
                result.add(dao.get(i));
                continue;
            }
            */

            for (int j = 0; j < parts.length; j++) {
                Pattern p = Pattern.compile("\\b" + parts[j] + "\\b");
                Matcher m = p.matcher(dao.get(i).getAllergeni().toLowerCase());
                if(!m.find()){
                    cont++;
                }
                if(cont == parts.length){
                    result.add(dao.get(i));
                    cont = 0;
                }
                else if(j == parts.length-1) {
                    cont = 0;
                }
            }
        }
        return conversioni.toDTO(result);
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
