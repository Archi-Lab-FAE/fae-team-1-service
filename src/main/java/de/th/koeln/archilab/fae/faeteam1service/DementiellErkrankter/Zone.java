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

    @ManyToOne
    @JoinColumn( name = "DEMENTIELLERKRANKTER_ID", referencedColumnName = "ID" )
    private DementiellErkrankter dementiellErkrankter;

    protected Zone() {
        this.id = UUID.randomUUID();
    }

    public void setDementiellErkrankter(DementiellErkrankter dementiellErkrankter) {
        this.dementiellErkrankter = dementiellErkrankter;
    }
}
