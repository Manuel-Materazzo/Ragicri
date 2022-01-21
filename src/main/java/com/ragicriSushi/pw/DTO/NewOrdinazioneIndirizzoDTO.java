package com.ragicriSushi.pw.DTO;


import com.ragicriSushi.pw.DAO.IndirizzoDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NewOrdinazioneIndirizzoDTO {

    private boolean pagato;

    private int tavolo;

    private int persone;

    private String tipologia;

    private String OrarioConsegna;

    private List<NewPiattoOrdinatoDTO> piattiOrdinati;

    private int idIndirizzo;

    public boolean getPagato(){
        return this.pagato;
    }

}
