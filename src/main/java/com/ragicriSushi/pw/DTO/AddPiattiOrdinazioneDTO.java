package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AddPiattiOrdinazioneDTO {

    private int id;

    private List<NewPiattoOrdinatoDTO> piattiOrdinati;

}
