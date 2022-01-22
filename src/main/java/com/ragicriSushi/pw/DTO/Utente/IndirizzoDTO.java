package com.ragicriSushi.pw.DTO.Utente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class IndirizzoDTO {

    private int idIndirizzo;

    private String via;

    private String provincia;

    private int CAP;

    private int civico;

}
