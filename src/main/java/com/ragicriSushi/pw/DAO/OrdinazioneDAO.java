package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Ordinazione")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrdinazioneDAO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int idOrdinazione;

    @Column(nullable = false)
    private String tipologia;

    @Column(nullable = false)
    private boolean pagato;

    @Column
    private int tavolo;

    @Column
    private int persone;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "ordinazione")
    List<PiattoOrdinato> piattiOrdinati;

    //@Column
    //private datetime OrarioConsegna;

    public boolean getPagato() {
        return this.pagato;
    }

}
