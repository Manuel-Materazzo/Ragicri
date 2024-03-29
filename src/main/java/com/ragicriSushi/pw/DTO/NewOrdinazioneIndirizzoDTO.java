package com.ragicriSushi.pw.DTO;


import com.ragicriSushi.pw.DTO.OrdinazioniPiatto.NewPiattoOrdinatoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NewOrdinazioneIndirizzoDTO {

    private boolean pagato;

    private boolean preparato;

    private boolean consegnato;

    private int tavolo;

    private int persone;

    private String tipologia;

    private String OrarioConsegna;

    private List<NewPiattoOrdinatoDTO> piattiOrdinati;

    private int idIndirizzo;

    public boolean getPagato() {
        return pagato;
    }

    public void setPagato(boolean pagato) {
        this.pagato = pagato;
    }

    public boolean getPreparato() {
        return preparato;
    }

    public void setPreparato(boolean preparato) {
        this.preparato = preparato;
    }

    public boolean getConsegnato() {
        return consegnato;
    }

    public void setConsegnato(boolean consegnato) {
        this.consegnato = consegnato;
    }
}
