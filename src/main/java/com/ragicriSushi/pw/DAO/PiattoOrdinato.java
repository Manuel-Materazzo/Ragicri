package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PiattoOrdinato")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PiattoOrdinato {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn
    OrdinazioneDAO ordinazione;

    @ManyToOne
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
