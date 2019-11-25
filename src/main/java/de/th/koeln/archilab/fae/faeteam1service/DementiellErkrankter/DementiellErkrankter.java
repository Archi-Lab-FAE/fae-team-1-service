package de.th.koeln.archilab.fae.faeteam1service.DementiellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
//@Table( name = "dementiellErkrankte")
public class DementiellErkrankter implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String vorname;
    private Boolean zustimmung;
    private Integer schuhgroesse;

    @OneToMany ( targetEntity = Zone.class, mappedBy = "dementiellErkrankter" )
    private List<Zone> zonen;

    protected DementiellErkrankter() {
        this.id = UUID.randomUUID();
    }
    public void setZonen(List<Zone> zonen) {
        this.zonen = zonen;
    }
}
