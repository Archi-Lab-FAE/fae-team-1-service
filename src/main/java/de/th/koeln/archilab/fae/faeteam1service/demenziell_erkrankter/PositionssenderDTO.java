package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PositionssenderDTO implements Serializable {
    private String id;
    private Date letzteWartung;

    public PositionssenderDTO() {
        this.id = UUID.randomUUID().toString();
    }
}
