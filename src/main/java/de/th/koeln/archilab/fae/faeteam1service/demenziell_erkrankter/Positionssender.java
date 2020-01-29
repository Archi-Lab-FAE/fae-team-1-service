package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Positionen")
public class Positionssender implements Serializable {

    @Id
    private String id;
    private Date letzteWartung;

    public Positionssender() {
        this.id = UUID.randomUUID().toString();
    }

    public Positionssender(PositionssenderDTO positionssenderDTO) {
        this.setId(positionssenderDTO.getId());
        this.setLetzteWartung(positionssenderDTO.getLetzteWartung());
    }
}

