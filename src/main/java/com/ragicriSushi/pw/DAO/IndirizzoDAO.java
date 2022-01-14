package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Indirizzo")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class IndirizzoDAO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int idIndirizzo;

    @Column(nullable = false)
    private String via;

    @Column(nullable = false)
    private String provincia;

    @Column(nullable = false)
    private int CAP;

    @Column(nullable = false)
    private int civico;

    @OneToOne(mappedBy = "Indirizzo")
    private UtenteDAO utenteDAO;
}
