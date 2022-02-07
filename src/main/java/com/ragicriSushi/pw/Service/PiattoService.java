package com.ragicriSushi.pw.Service;

import com.ragicriSushi.pw.DAO.PiattoDAO;
import com.ragicriSushi.pw.DTO.*;
import com.ragicriSushi.pw.DTO.Piatto.AddPiattoDTO;
import com.ragicriSushi.pw.DTO.Piatto.PiattoDTO;
import com.ragicriSushi.pw.DTO.Piatto.TipologieDTO;
import com.ragicriSushi.pw.Repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
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

    public PiattoDTO delete(NumeroDTO dtoIn){
        Optional<PiattoDAO> dao = piattoRepository.findPiattoByNumero(dtoIn.getNumero());

        if (dao.isPresent()){
            PiattoDTO dto = conversioni.toDTO(dao.get());
            piattoRepository.delete(dao.get());
            return dto;
        }
        else {
            return null;
        }
    }

    public PiattoDTO update(AddPiattoDTO dto){
        Optional<PiattoDAO> dao = piattoRepository.findPiattoByNumero(dto.getNumero());
        if(dao.isPresent()){
            dao.get().setAllergeni(dto.getAllergeni());
            if(dto.getImg()!=null) {
                dao.get().setImg(dto.getImg());
            }

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

    public PiattoDTO add(AddPiattoDTO dto){
        if(checkByNumero(dto.getNumero())){
            return null;
        }

        PiattoDAO dao = new PiattoDAO();
        dao.setNome(dto.getNome());
        dao.setPrezzo(dto.getPrezzo());
        dao.setNumero(dto.getNumero());
        dao.setImg(dto.getImg());
        dao.setAllergeni(dto.getAllergeni());
        dao.setTipologia(dto.getTipologia());

        piattoRepository.save(dao);
        return conversioni.toDTO(dao);
    }

    public boolean checkByNumero(int numero){
        Optional<PiattoDAO> dao = piattoRepository.findPiattoByNumero(numero);

        if(dao.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

    public PiattoDTO getNumero(int numero){
        Optional<PiattoDAO> dao = piattoRepository.findPiattoByNumero(numero);
        if(dao.isPresent()){
            return conversioni.toDTO(dao.get());
        }
        else {
            return null;
        }
    }

    public TipologieDTO getTipologie(){
        List<PiattoDAO> dao = piattoRepository.getTipologie();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < dao.size(); i++) {
            list.add(dao.get(i).getTipologia());
        }

        TipologieDTO dto = new TipologieDTO();
        dto.setTipologie(list);
        
        return dto;
    }

    public String saveImage(MultipartFile multipartFile,String nomeFile) {
        //controllare se il file è vuoto
        if (multipartFile.isEmpty()) {
          throw new IllegalStateException("Cannot upload empty file");
      }
        //controllare se il file è un'immagine
       if (!Arrays.asList("image/png","image/bmp","image/gif","image/jpeg").contains(multipartFile.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", multipartFile.getContentType());
        metadata.put("Content-Length", String.valueOf(multipartFile.getSize()));

        String dirName = "C:\\immaginiRagicri";

        try {
            //File actualFile = new File (dirName, nomeFile+"."+multipartFile.getOriginalFilename().split("\\.")[0]);
            File actualFile = new File (dirName, nomeFile+"."+ StringUtils.getFilenameExtension(multipartFile.getOriginalFilename()));
            multipartFile.transferTo(actualFile);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        String prova=(dirName+"/"+nomeFile+"."+StringUtils.getFilenameExtension(multipartFile.getOriginalFilename())).replace("\\","/");
        return prova;
    }
}
