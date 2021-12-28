package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Utente")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UtenteDAO {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;
}
