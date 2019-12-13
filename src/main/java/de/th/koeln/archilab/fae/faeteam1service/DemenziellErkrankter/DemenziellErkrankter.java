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
    private String id;
    private String name;
    private String vorname;
    private Boolean zustimmung;
    private Integer schuhgroesse;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "demenziellErkrankte_id")
    private List<KontaktPerson> kontaktPersonen;
    public void setKontaktPersonen(List<KontaktPerson> kontaktPersonen) { this.kontaktPersonen = kontaktPersonen; }
    public List<KontaktPerson> getKontaktPersonen() { return kontaktPersonen; }

    @OneToMany(targetEntity = Positionssender.class, mappedBy = "demenziellErkrankter")
    private List<Positionssender> positionen;
    public void setPositionssender(List<Positionssender> Positionen) {
        this.positionen = Positionen;
    }


    public DemenziellErkrankter() {
        this.id = UUID.randomUUID().toString();
    }
    public DemenziellErkrankterOutDaten toOutFormat(){
        DemenziellErkrankterOutDaten outDaten = new DemenziellErkrankterOutDaten();
        outDaten.setId(this.id);
        outDaten.setName(this.name);
        outDaten.setVorname(this.vorname);
        for (KontaktPerson kontaktPerson: this.kontaktPersonen) {
            if (kontaktPerson.hatZugestimmt()) {
                outDaten.addKontaktperson(kontaktPerson.toOutFormat());
            }
        }
        return outDaten;
    }
}
