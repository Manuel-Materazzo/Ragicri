package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
