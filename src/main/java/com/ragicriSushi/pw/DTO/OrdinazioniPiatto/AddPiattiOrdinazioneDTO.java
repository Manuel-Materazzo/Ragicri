package com.ragicriSushi.pw.DTO.OrdinazioniPiatto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AddPiattiOrdinazioneDTO {

    private int tavolo;

    private List<NewPiattoOrdinatoDTO> piattiOrdinati;

}
