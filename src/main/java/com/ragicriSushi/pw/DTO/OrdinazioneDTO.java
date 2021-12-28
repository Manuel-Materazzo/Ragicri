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

    private double pagato;

    private int tavolo;

    private int persone;

    //private datetime OrarioConsegna;

    private List<PiattoOrdinatoDTO> piattiOrdinati;

}
