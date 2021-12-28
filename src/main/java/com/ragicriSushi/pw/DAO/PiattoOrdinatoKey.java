package com.ragicriSushi.pw.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class PiattoOrdinatoKey implements Serializable {

    int idPiatto;

    int idOrdinazione;

}
