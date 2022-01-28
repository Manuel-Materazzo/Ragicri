package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;

import java.time.LocalTime;
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

    private boolean preparato;

    private boolean consegnato;

    @Column
    private int tavolo;

    @Column
    private int persone;

    @Column
    private LocalTime orarioConsegna;

    @ManyToOne
    @JoinColumn(name="indirizzo", nullable = true)
    private IndirizzoDAO indirizzo;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "ordinazione")
    List<PiattoOrdinato> piattiOrdinati;

    public boolean getPagato() {
        return pagato;
    }

    public boolean getPreparato() {
        return preparato;
    }

    public boolean getConsegnato() {
        return consegnato;
    }

}
