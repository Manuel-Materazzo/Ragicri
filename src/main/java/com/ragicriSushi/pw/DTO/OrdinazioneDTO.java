package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrdinazioneDTO {

    private int idOrdinazione;

    private String tipologia;

    private boolean pagato;

    private int tavolo;

    private int persone;

    private String OrarioConsegna;

    private int idIndirizzo;

    private List<PiattoOrdinatoDTO> piattiOrdinati;

    public void setPagato(boolean a){
        this.pagato = a;
    }

    public boolean getPagato(){
        return this.pagato;
    }

}
