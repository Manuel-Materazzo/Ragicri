package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UtenteDTO {

    private int id;

    private String nome;

    private String ruolo;

    private String username;

    private String password;

    private IndirizzoDTO indirizzoDTO;

}
