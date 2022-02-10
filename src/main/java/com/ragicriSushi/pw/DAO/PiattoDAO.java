package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Piatto")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PiattoDAO {

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "piatto")
    List<PiattoOrdinato> piattiOrdinati;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int idPiatto;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private int numero;
    @Column(nullable = false)
    private String tipologia;
    @Column(nullable = false)
    private double prezzo;
    @Column
    private String allergeni;
    @Column
    private String img;

}
