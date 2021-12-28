package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PiattoOrdinatoDTO {

    private int numeretto;

    private double prezzo;

    private String nome;

    private boolean consegnato;

    private int quantita;

}
