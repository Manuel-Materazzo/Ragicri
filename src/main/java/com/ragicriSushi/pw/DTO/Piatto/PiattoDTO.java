package com.ragicriSushi.pw.DTO.Piatto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PiattoDTO {

    private String nome;

    private int numero;

    private String tipologia;

    private String allergeni;

    private double prezzo;

    private String img;

}
