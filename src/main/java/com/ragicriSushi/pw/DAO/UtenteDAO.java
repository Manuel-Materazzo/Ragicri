package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Utente")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UtenteDAO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int idUtente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String ruolo;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Indirizzo", referencedColumnName = "idIndirizzo", nullable = true)
    private IndirizzoDAO Indirizzo;
}
