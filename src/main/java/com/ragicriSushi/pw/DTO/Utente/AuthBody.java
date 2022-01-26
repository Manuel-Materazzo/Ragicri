package com.ragicriSushi.pw.DTO.Utente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthBody {
    private String username;
    private String password;
}
