package com.ragicriSushi.pw.DTO.Utente;

import com.ragicriSushi.pw.DTO.Role.RoleDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateUtenteDto {

    private int id;

    private String nome;

    private String email;

    private String username;

    private String password;

    private IndirizzoDTO indirizzo;

    private String ruolo;
}
