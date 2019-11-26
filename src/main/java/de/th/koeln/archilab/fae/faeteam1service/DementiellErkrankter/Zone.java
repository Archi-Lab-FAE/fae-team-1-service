package de.th.koeln.archilab.fae.faeteam1service.DementiellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@Table( name = "zonen" )
public class Zone {
    @Id
    private UUID id;
    private double[] koordinaten;
    private Boolean sicher;

    protected Zone() {
        this.id = UUID.randomUUID();
    }

}
