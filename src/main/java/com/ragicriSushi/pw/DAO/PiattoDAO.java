package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Piatto")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PiattoDAO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int idPiatto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String tipologia;

    @Column(nullable = false)
    private double prezzo;

    @Column
    private String allergeni;

    @Column
    private boolean consegnato;

    @Column
    private String img;

    public boolean getConsegnato(){
        return this.consegnato;
    }

}
