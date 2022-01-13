package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class IndirizzoDTO {

    private String via;

    private String provincia;

    private int CAP;

    private int civico;

}
