package com.ragicriSushi.pw.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NewOrdinazioneDTO {

    private boolean pagato;

    private boolean preparato;

    private boolean consegnato;

    private int tavolo;

    private int persone;

    private String tipologia;

    //private datetime OrarioConsegna;

    private List<NewPiattoOrdinatoDTO> piattiOrdinati;

    public boolean getPagato() {
        return pagato;
    }

    public boolean getPreparato() {
        return preparato;
    }

    public boolean getConsegnato() {
        return consegnato;
    }

    public void setPagato(boolean pagato) {
        this.pagato = pagato;
    }

    public void setPreparato(boolean preparato) {
        this.preparato = preparato;
    }

    public void setConsegnato(boolean consegnato) {
        this.consegnato = consegnato;
    }
}
