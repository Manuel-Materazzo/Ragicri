package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
public class PiattoDTO {

    private String nome;

    private int numero;

    private String tipologia;

    private String allergeni;

    private boolean consegnato;

    private double prezzo;

    private String img;

}
