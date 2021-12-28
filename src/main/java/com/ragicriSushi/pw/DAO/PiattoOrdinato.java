package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PiattoOrdinato")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PiattoOrdinato {

    @EmbeddedId
    PiattoOrdinatoKey id;

    @ManyToOne
    @MapsId("idOrdinazione")
    @JoinColumn
    OrdinazioneDAO ordinazione;

    @ManyToOne
    @MapsId("idPiatto")
    @JoinColumn
    PiattoDAO piatto;

    @Column
    boolean consegnato = false;

    @Column
    int quantita;

    public boolean getConsegnato(){
        return this.consegnato;
    }

}
