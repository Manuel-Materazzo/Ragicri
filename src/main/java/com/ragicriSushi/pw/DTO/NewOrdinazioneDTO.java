package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NewOrdinazioneDTO {

    private boolean pagato;

    private int tavolo;

    private int persone;

    private String tipologia;

    //private datetime OrarioConsegna;

    private List<NewPiattoOrdinatoDTO> piattiOrdinati;

    public boolean getPagato(){
        return this.pagato;
    }

}
