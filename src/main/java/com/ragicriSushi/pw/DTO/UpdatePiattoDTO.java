package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdatePiattoDTO {

    private int id;

    private String nome;

    private int numero;

    private String tipologia;

    private String allergeni;

    private double prezzo;

    private String img;

}
