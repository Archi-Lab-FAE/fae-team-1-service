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
@Table( name = "demenziellErkrankte")
public class DemenziellErkrankter implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String vorname;
    private Boolean zustimmung;
    private Integer schuhgroesse;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "demenziellErkrankte_id")
    private List<Zone> zonen;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "demenziellErkrankte_id")
    private List<KontaktPerson> kontaktPersonen;
    public void setKontaktPersonen(List<KontaktPerson> kontaktPersonen) { this.kontaktPersonen = kontaktPersonen; }
    public List<KontaktPerson> getKontaktPersonen() { return kontaktPersonen; }

    @OneToMany(targetEntity = Positionssender.class, mappedBy = "demenziellErkrankter")
    private List<Positionssender> Positionen;
    public void setPositionssender(List<Positionssender> Positionen) {
        this.Positionen = Positionen;
    }


    public DemenziellErkrankter() {
        this.id = UUID.randomUUID();
    }
    public void setZonen(List<Zone> zonen) {
        this.zonen = zonen;
    }

    public UUID getId() {
        return id;
    }
}
