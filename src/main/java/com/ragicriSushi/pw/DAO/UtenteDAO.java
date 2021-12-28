package com.ragicriSushi.pw.DAO;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "UtenteDAO")
public class UtenteDAO {

    @Id
    @GeneratedValue
    private Integer id;
}
