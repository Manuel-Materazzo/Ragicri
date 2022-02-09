package com.ragicriSushi.pw.DTO.Ordinazione;

import com.ragicriSushi.pw.DTO.OrdinazioniPiatto.PiattoOrdinatoDTO;
import com.ragicriSushi.pw.DTO.Utente.IndirizzoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrdinazioneDTO {

    private int idOrdinazione;

    private String tipologia;

    private boolean pagato;

    private boolean preparato;

    private boolean consegnato;

    private int tavolo;

    private int persone;

    private String OrarioConsegna;

    private int idIndirizzo;

    private IndirizzoDTO indirizzo;

    private List<PiattoOrdinatoDTO> piattiOrdinati;

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
