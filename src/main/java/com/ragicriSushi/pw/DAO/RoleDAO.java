package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ruolo")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoleDAO {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="ruolo")
    private List<UtenteDAO> utenti;
}
