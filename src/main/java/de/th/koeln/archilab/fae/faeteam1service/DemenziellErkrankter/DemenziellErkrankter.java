package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@Table( name = "dementiellErkrankte")
public class DemenziellErkrankter implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String vorname;
    private Boolean zustimmung;
    private Integer schuhgroesse;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dementiellErkrankte_id")
    private List<Zone> zonen;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dementiellErkrankte_id")
    private List<KontaktPerson> kontaktPersonen;

    @OneToOne(targetEntity = Positionssender.class, mappedBy = "demenziellErkrankter")
    private List<Positionssender> Positionen;
    public void setPositionssender(List<Positionssender> Positionen) {
        this.Positionen = Positionen;
    }


    protected DemenziellErkrankter() {
        this.id = UUID.randomUUID();
    }
    public void setZonen(List<Zone> zonen) {
        this.zonen = zonen;
    }
}
